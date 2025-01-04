package com.spring.delivery.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.spring.delivery.domain.response.ResponseSignature;
import com.spring.delivery.util.enums.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryServiceImpl implements CloudinaryService {
    Cloudinary cloudinary;
    @Value("${app.service.cloudinary.folder}")
    @NonFinal
    String rootFolder;

    @Override
    public String generateHLS(String publicId) {
        return cloudinary.url().resourceType("video")
                .format("m3u8")
                .type("upload")
                .transformation(new Transformation().audioCodec("aac"))
                .publicId(publicId)
                .generate();
    }

    @Override
    public String generateImage(String publicId) {
        return cloudinary.url().generate(publicId);
    }

    @Override
    public ResponseSignature getSignature(String publicId, Tag tag) {
        String folder = rootFolder + "/" + tag.getName();
//        Hard code timestamp
        long timestamp = System.currentTimeMillis() / 1000;
        Map<String, Object> params = ObjectUtils.asMap(
                "public_id", publicId,
                "timestamp", timestamp,
                "folder", folder);
        String signature = cloudinary.apiSignRequest(params, cloudinary.config.apiSecret);
        String resourceType = Tag.AUDIO == tag ? "video" : "image";
        return ResponseSignature.builder()
                .apiKey(cloudinary.config.apiKey)
                .folder(folder)
                .signature(signature)
                .timestamp(timestamp)
                .resourceType(resourceType)
                .type("upload")
                .publicId(publicId)
                .build();
    }

    @Async
    @Override
    public CompletableFuture<ResponseCloudinaryUpload> upload(MultipartFile file, Tag tag) {
        try {
            InputStream inputStream = file.getInputStream();
            String folder = rootFolder + "/" + tag.getName();
            Map<String, Object> response = cloudinary.uploader().uploadLarge(inputStream, ObjectUtils.asMap(
                    "resource_type", "auto",
                    "tags", tag.name(),
                    "folder", folder));
            if (response.containsKey("error"))
                return CompletableFuture.completedFuture(null);

            ResponseCloudinaryUpload result = new ResponseCloudinaryUpload();
            result.setPublicId(response.get("public_id").toString());
            result.setResourceType(response.get("resource_type").toString());
            result.setCreatedAt(LocalDateTime.parse(response.get("created_at").toString(), DateTimeFormatter.ISO_DATE_TIME));
            return CompletableFuture.completedFuture(result);
        } catch (IOException e) {
            return CompletableFuture.completedFuture(null);
        }
    }
}
