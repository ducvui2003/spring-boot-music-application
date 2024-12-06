package com.spring.delivery.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.spring.delivery.domain.request.RequestCreateResource;
import com.spring.delivery.domain.response.ResponseCloudinaryUpload;
import com.spring.delivery.domain.response.ResponseSignature;
import com.spring.delivery.util.enums.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

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
        return ResponseSignature.builder()
                .apiKey(cloudinary.config.apiKey)
                .folder(folder)
                .signature(signature)
                .timestamp(timestamp)
                .resourceType("video")
                .type("upload")
                .publicId(publicId)
                .build();
    }
}
