package com.spring.delivery.domain.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HLSCloudinaryResponse {
    String urlOriginal;
    String secureUrlOriginal;
    String urlM3u4;
    String secureUrlM3u4;
    String publicId;
    Instant createdAt;
}
