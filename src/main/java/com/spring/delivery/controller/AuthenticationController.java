package com.spring.delivery.controller;

import com.spring.delivery.config.properties.CookieProperties;
import com.spring.delivery.domain.request.RequestLogin;
import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.domain.request.RequestVerify;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.User;
import com.spring.delivery.service.authentication.AuthenticationService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.anotation.ApiMessage;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    UserMapper userMapper = UserMapper.INSTANCE;
    CookieProperties cookieProperties;
    AuthenticationService authenticationService;
    AuthenticationManagerBuilder authenticationManagerBuilder;
    SecurityUtil securityUtil;

    @ApiMessage("Login")
    @PostMapping("/login")
    public ResponseEntity<ResponseAuthentication> login(@Valid @RequestBody RequestLogin userLogin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseAuthentication response = authenticationService.login();
        ResponseCookie cookie = securityUtil.updateRefreshToken(response.getRefreshToken());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
    }

    @ApiMessage("Register")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RequestRegister userRegister) {
        authenticationService.register(userRegister);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // Sử dụng để client lấy lại data người dùng khi F5 trang
    // (người dùng đã login trước đó và cookie đã set refresh)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SHIPPER')")
    @ApiMessage("Get info account")
    @GetMapping("/account")
    public ResponseEntity<ResponseAuthentication.UserGetAccount> getAccount() {
        String email = SecurityUtil.getCurrentUserLogin().orElse("");
        User user = authenticationService.getUserByEmail(email);
        if (user == null)
            throw new AppException("Xử lý if else user trong hàm getAccount của AuthenticationController");

        ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);
        return ResponseEntity.ok().body(new ResponseAuthentication.UserGetAccount(userDTO));
    }

    // Sử dụng để lấy lại access token khi hết hạn
    @ApiMessage("Refresh token")
    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseAuthentication> refreshAccessToken(
            @CookieValue(name = "refresh_token") String refreshToken) {
        securityUtil.validateRefreshToken(refreshToken);
        //  Check user by refresh authCode
        String email =
                SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));

        ResponseAuthentication responseBody = authenticationService.getAccessToken(email);

        return ResponseEntity.ok().body(responseBody);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ApiMessage("Logout")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @CookieValue(
                    name = "${app.cookie.key.refreshToken}",
                    defaultValue = "${app.cookie.defaultValue.refreshToken}")
            String refreshToken,
            HttpServletRequest request)
            throws AppException {
        if (refreshToken.equals(cookieProperties.getRefreshTokenDefault()))
            throw new AppException(AppErrorCode.REFRESH_TOKEN_NOT_FOUND);
        String email =
                SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        String accessToken = SecurityUtil.getAccessTokenFromRequest(request)
                .orElseThrow(() -> new AppException(AppErrorCode.ACCESS_TOKEN_NOT_FOUND));
        this.authenticationService.logout(email, accessToken, refreshToken);
        SecurityContextHolder.getContext().setAuthentication(null);
        ResponseCookie cookie = securityUtil.clearRefreshToken();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(null);
    }

    @PostMapping("/verify")
    @ApiMessage("Verify success")
    public ResponseEntity<Void> verify(@RequestHeader String email, @Valid @RequestBody RequestVerify request) {
        authenticationService.verify(email, request.otp());
        return ResponseEntity.ok().build();
    }
}
