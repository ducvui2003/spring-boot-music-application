/**
 * Class: ResourceServiceImpl
 *
 * @author ducvui2003
 * @created 13/10/24
 */
package com.spring.delivery.service.business.resource;

import com.spring.delivery.domain.request.RequestCreateResource;
import com.spring.delivery.domain.response.ResourceResponse;
import com.spring.delivery.model.Resource;
import com.spring.delivery.repository.ResourceRepository;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.enums.Tag;
import com.spring.delivery.util.validation.ValidationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ResourceServiceImpl implements ResourceService {
    CloudinaryService cloudinaryService;
    ResourceRepository resourceRepository;

    @Override
    public ResourceResponse createResource(RequestCreateResource resource) {
        String url = cloudinaryService.generateImage(resource.publicId());
        Resource savedResource = resourceRepository.save(Resource.builder()
                .url(url)
                .tag(resource.tag())
                .name(resource.name())
                .publicId(resource.publicId())
                .build());
        resourceRepository.save(savedResource);
        if (resource.tag().equals(Tag.AUDIO))
//        Audio
            url = cloudinaryService.generateHLS(savedResource.getPublicId());
        return ResourceResponse.builder()
                .id(savedResource.getId())
                .url(url)
                .tag(savedResource.getTag())
                .build();
    }

    @Deprecated
    @Override
    public ResourceResponse createResource(RequestCreateResource resource, ValidationStrategy validationStrategy) {
//        if (validationStrategy.isValid(resource.multipartFile())) {
//            return createResource(resource);
//        }
        return null;
    }

    @Override
    public ResourceResponse getResource(Long id) {
        Resource resource = resourceRepository.findById(id).orElse(null);
        if (resource == null)
            return null;
        String url = cloudinaryService.generateHLS(resource.getPublicId());
        return ResourceResponse.builder()
                .id(resource.getId())
                .url(url)
                .tag(resource.getTag())
                .build();
    }
}
