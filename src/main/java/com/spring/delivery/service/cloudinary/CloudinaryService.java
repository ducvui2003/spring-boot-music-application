package com.spring.delivery.service.cloudinary;


import com.spring.delivery.domain.request.HLSCloudinaryRequest;
import com.spring.delivery.domain.response.ResponseVideoCloudinary;

public interface CloudinaryService {
    public ResponseVideoCloudinary uploadMp3(HLSCloudinaryRequest hlsCloudinaryResponse) throws Exception;
}
