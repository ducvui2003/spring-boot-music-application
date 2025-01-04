package com.spring.delivery.service.cloudinary;


import com.spring.delivery.domain.response.ResponseSignature;
import com.spring.delivery.util.enums.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface CloudinaryService {
    String generateHLS(String publicId);

    String generateImage(String publicId);

    ResponseSignature getSignature(String publicId, Tag tag);

    CompletableFuture<ResponseCloudinaryUpload> upload(MultipartFile file, Tag tag);
}
