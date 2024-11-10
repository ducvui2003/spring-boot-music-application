package com.spring.delivery.service.business.resource;

import com.spring.delivery.domain.request.RequestCreateResource;
import com.spring.delivery.domain.response.ResourceResponse;
import com.spring.delivery.util.validation.ValidationStrategy;

public interface ResourceService {
    ResourceResponse createResource(RequestCreateResource resource);

    @Deprecated
    ResourceResponse createResource(RequestCreateResource resource, ValidationStrategy validationStrategy);

    ResourceResponse getResource(Long id);
}
