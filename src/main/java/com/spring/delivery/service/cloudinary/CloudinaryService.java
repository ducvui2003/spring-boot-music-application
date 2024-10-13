package com.spring.delivery.service.cloudinary;


import com.spring.delivery.domain.request.RequestUploadResource;
import com.spring.delivery.domain.response.ResponseCloudinaryUpload;

public interface CloudinaryService {
    ResponseCloudinaryUpload uploadAudio(RequestUploadResource hlsCloudinaryResponse) throws Exception;

    ResponseCloudinaryUpload uploadImage(RequestUploadResource hlsCloudinaryResponse) throws Exception;

    String generateHLS(String publicId);
}
