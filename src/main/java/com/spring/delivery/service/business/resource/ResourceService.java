package com.spring.delivery.service.business.resource;

import com.spring.delivery.domain.request.RequestUploadResource;
import com.spring.delivery.domain.response.ResourceResponse;
import com.spring.delivery.util.validation.ValidationStrategy;

public interface ResourceService {
    ResourceResponse createResource(RequestUploadResource resource);

    ResourceResponse createResource(RequestUploadResource resource, ValidationStrategy validationStrategy);

}
