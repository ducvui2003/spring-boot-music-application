package com.spring.delivery.service.authentication;

import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.JwtPayload;
import com.spring.delivery.model.Permission;
import com.spring.delivery.model.Role;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.RoleRepository;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.token.TokenService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.enums.AuthType;
import com.spring.delivery.util.enums.RedisNameSpace;
import com.spring.delivery.util.enums.RoleEnum;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    TokenService tokenService;
    UserMapper userMapper = UserMapper.INSTANCE;
    SecurityUtil securityUtil;
    RoleRepository roleRepository;
    VerifyService verifyService;

    @Override
    public void register(RequestRegister request) {
        userRepository.findByEmailAndVerifiedIsTrue(request.getEmail()).ifPresent(user -> {
            throw new AppException(AppErrorCode.EMAIL_EXISTED);
        });
        userRepository.deleteUserByEmailAndVerifiedIsFalse(request.getEmail());
        User user = userMapper.toUser(request);
        user.setVerified(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthType(AuthType.USERNAME_PASSWORD);
        user.setRole(roleRepository.findByName(RoleEnum.USER));
        verifyService.sendOtp(user.getEmail());
        userRepository.save(user);
    }

    @Override
    public ResponseAuthentication getAccessToken(String email) {
        User user = this.getUserByEmail(email);

        if (user == null) throw new AppException(AppErrorCode.USER_NOT_FOUND);

        ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);

        JwtPayload jwtPayload = getJwtPayload(user);

        String accessToken = securityUtil.createAccessToken(jwtPayload);

        tokenService.saveToken(user.getEmail(), accessToken);

        String refreshToken = securityUtil.createRefreshToken(jwtPayload);

        return ResponseAuthentication.builder()
                .user(userDTO)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmailAndVerifiedIsTrue(email).orElse(null);
    }


    @Override
    public void logout(String email, String accessToken, String refreshToken) {
        tokenService.saveToken(accessToken, email);
        tokenService.saveToken(refreshToken, email);
    }

    @Override
    public boolean isVerify(String email) {
        return userRepository.existsByEmailAndVerifiedIsTrue(email);
    }

    @Override
    public ResponseAuthentication login() {
//        Không cần case TH user không tồn tại vì đã được nạp vào context
        String email = SecurityUtil.getCurrentUserLogin().get();
        log.info("email {}", email);

        User user = this.getUserByEmail(email);

        ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);
        JwtPayload jwtPayload = getJwtPayload(user);

        String accessToken = securityUtil.createAccessToken(jwtPayload);

        String refreshToken = securityUtil.createRefreshToken(jwtPayload);

        return ResponseAuthentication.builder()
                .user(userDTO)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void createUserOAuth2(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        if (userRepository.existsByEmail(email)) return;

        Role roleUser = roleRepository.findByName(RoleEnum.USER);
        User user = User.builder()
                .email(email)
                .fullName(oAuth2User.getAttribute("name"))
                .verified(true)
                .role(roleUser)
                .authType(AuthType.OAUTH2)
                .build();
        userRepository.save(user);
    }

    private JwtPayload getJwtPayload(User user) {
        return JwtPayload.builder()
                .email(user.getEmail())
                .user(userMapper.toUserPayload(user))
                .role(user.getRole().getName().name())
                .permissions(user.getRole().getPermissions().stream().map(Permission::getName).toList())
                .timeExpiredPlus(1)
                .build();
    }

    @Override
    public void verify(String email, String otp) {
        verifyService.verifyOtp(email, otp);
    }
}
