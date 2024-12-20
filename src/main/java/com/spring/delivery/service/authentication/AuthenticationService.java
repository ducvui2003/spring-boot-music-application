package com.spring.delivery.service.authentication;

import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthenticationService {
    void register(RequestRegister request);

    ResponseAuthentication login();

    ResponseAuthentication getAccessToken(String email);

    void logout(String email, String accessToken, String refreshToken);

    boolean isVerify(String email);

    void createUserOAuth2(OAuth2User oAuth2User);

    User getUserByEmail(String email);

    void verify(String email, String otp);

    void resendOtp(String email);
}
