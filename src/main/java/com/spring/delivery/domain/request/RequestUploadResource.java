package com.spring.delivery.domain.request;

import com.spring.delivery.util.enums.Tag;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record RequestUploadResource(MultipartFile multipartFile,
                                    String name,
                                    String parent,
                                    Tag tag
) {
}
