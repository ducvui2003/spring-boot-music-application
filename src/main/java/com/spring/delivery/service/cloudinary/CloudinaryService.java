package com.spring.delivery.service.cloudinary;


import com.spring.delivery.domain.request.HLSCloudinaryRequest;
import com.spring.delivery.domain.response.HLSCloudinaryResponse;

public interface CloudinaryService {
    public HLSCloudinaryResponse uploadMp3(HLSCloudinaryRequest hlsCloudinaryResponse) throws Exception;
}
