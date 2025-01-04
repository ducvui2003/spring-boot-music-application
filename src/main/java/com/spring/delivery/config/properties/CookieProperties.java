package com.spring.delivery.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CookieProperties {
    @Value("${app.cookie.key.refreshToken}")
    String refreshToken;

    @Value("${app.cookie.defaultValue.refreshToken}")
    String refreshTokenDefault;
}
