package com.spring.delivery.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Value("${app.service.cloudinary.cloud-name}")
    String cloudName;
    @Value("${app.service.cloudinary.api-key}")
    String apiKey;
    @Value("${app.service.cloudinary.api-secret}")
    String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret, "access_mode", "public", "secure", true));
    }

    @Bean
    @Qualifier("defaultTransformation")
    public Transformation transformation() {
        return new Transformation().crop("scale").chain()
                .quality("auto").chain()
                .fetchFormat("auto");
    }

    @Bean
    @Qualifier("hlsTransformation")
    public Transformation transformationHLS() {
        return new Transformation()
                .quality("auto") // Set quality
                .audioCodec("aac") // Set audio codec
                .bitRate(128) // Set bitrate (optional)
                .audioCodec("aac").width(640)
                .height(360)
                .streamingProfile("hd")
                .fetchFormat("m3u4")
                .crop("limit");
    }
}
