package com.spring.delivery.domain.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HLSCloudinaryRequest {
    MultipartFile mp3;
    String name;
    String parent;
}
