package com.spring.delivery.service.cloudinary;


import com.spring.delivery.domain.request.RequestUploadResource;
import com.spring.delivery.domain.response.ResponseCloudinaryUpload;

public interface CloudinaryService {
    public ResponseCloudinaryUpload uploadAudio(RequestUploadResource hlsCloudinaryResponse) throws Exception;

    public ResponseCloudinaryUpload uploadImage(RequestUploadResource hlsCloudinaryResponse) throws Exception;

}
