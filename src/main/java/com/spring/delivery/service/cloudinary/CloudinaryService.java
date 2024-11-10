package com.spring.delivery.service.cloudinary;


import com.spring.delivery.domain.request.RequestCreateResource;
import com.spring.delivery.domain.response.ResponseCloudinaryUpload;
import com.spring.delivery.domain.response.ResponseSignature;
import com.spring.delivery.util.enums.Tag;

public interface CloudinaryService {
    String generateHLS(String publicId);

    String generateUrl(String publicId);

    ResponseSignature getSignature(String publicId, Tag tag);
}
