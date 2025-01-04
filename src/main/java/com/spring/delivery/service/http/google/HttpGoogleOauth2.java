package com.spring.delivery.service.http.google;

import com.spring.delivery.domain.request.RequestAuthorizationCode;
import com.spring.delivery.domain.response.ResponseGoogleAuthorizationCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "google-service", url = "https://oauth2.googleapis.com")
public interface HttpGoogleOauth2 {
    @PostMapping(value = "/token")
    ResponseGoogleAuthorizationCode getAccessToken(@RequestBody RequestAuthorizationCode requestAuthorizationCode);
}
