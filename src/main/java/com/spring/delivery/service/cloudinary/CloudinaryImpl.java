package com.spring.delivery.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.spring.delivery.domain.request.RequestUploadResource;
import com.spring.delivery.domain.response.ResponseCloudinaryUpload;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryImpl implements CloudinaryService {
    Cloudinary cloudinary;
    @Value("${app.service.cloudinary.folder}")
    @NonFinal
    String rootFolder;

    @Override
    public ResponseCloudinaryUpload uploadAudio(RequestUploadResource request) throws Exception {
        String publicId = rootFolder + "/" + request.parent() + "/" + request.name();
        // Define the eager transformations using Transformation objects
        Transformation transformation = new Transformation()
                .streamingProfile("hd");
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "resource_type", "video",
                "public_id", publicId,
                "eager", Arrays.asList(transformation),
                "eager_async", true
        );

        return getResponseCloudinaryUpload(request, uploadParams);
    }

    @Override
    public ResponseCloudinaryUpload uploadImage(RequestUploadResource request) throws Exception {
        String publicId = rootFolder + "/" + request.parent() + "/" + request.name();
        // Define the eager transformations using Transformation objects
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "resource_type", "image",
                "public_id", publicId,
                "eager_async", true
        );

        return getResponseCloudinaryUpload(request, uploadParams);
    }

    private ResponseCloudinaryUpload getResponseCloudinaryUpload(RequestUploadResource request, Map<String, Object> uploadParams) throws IOException {
        String publicId;
        Map uploadResult = cloudinary.uploader().upload(request.multipartFile().getBytes(), uploadParams);
        String secureUrlOriginal = (String) uploadResult.get("secure_url");
        String url = (String) uploadResult.get("url");
        publicId = (String) uploadResult.get("public_id");
        String assetId = (String) uploadResult.get("asset_id");
        Instant createdAt = Instant.parse(uploadResult.get("created_at").toString());
        return ResponseCloudinaryUpload.builder()
                .secureUrl(secureUrlOriginal)
                .publicId(publicId)
                .assetId(assetId)
                .url(url)
                .createdAt(createdAt)
                .build();
    }
}
