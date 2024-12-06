package com.spring.delivery.service.business.resource;

import com.spring.delivery.domain.request.RequestCreateResource;
import com.spring.delivery.domain.response.ResponseResource;
import com.spring.delivery.util.validation.ValidationStrategy;

public interface ResourceService {
    ResponseResource createResource(RequestCreateResource resource);

    @Deprecated
    ResponseResource createResource(RequestCreateResource resource, ValidationStrategy validationStrategy);

    ResponseResource getResource(Long id);
}
