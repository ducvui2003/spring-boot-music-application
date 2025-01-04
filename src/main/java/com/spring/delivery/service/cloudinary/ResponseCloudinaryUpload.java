package com.spring.delivery.service.cloudinary;

import com.google.type.DateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseCloudinaryUpload {
    String publicId;
    LocalDateTime createdAt;
    String resourceType;
}
