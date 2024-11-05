package com.spring.delivery.domain.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseCloudinaryUpload {
    String url;
    String secureUrl;
    String publicId;
    String assetId;
    Instant createdAt;
}
