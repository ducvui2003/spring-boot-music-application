package com.spring.delivery.service.authentication;

import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthenticationService {
    User register(RequestRegister request);

    ResponseAuthentication getAccessToken(String email);

    void logout(String email, String accessToken, String refreshToken);

    User getUserByEmail(String email);

    boolean isVerify(String email);
    
    ResponseAuthentication loginByEmail();

    void createUserOAuth2(OAuth2User oAuth2User);
}
