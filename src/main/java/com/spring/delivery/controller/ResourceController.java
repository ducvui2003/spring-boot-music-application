package com.spring.delivery.controller;

import com.spring.delivery.domain.request.RequestSignature;
import com.spring.delivery.domain.request.RequestCreateResource;
import com.spring.delivery.domain.response.ResourceResponse;
import com.spring.delivery.domain.response.ResponseSignature;
import com.spring.delivery.service.business.resource.ResourceService;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.enums.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resource")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceController {
    ResourceService resourceService;
    CloudinaryService cloudinaryService;

    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponse> getResource(@PathVariable("id") Long id) {
        ResourceResponse response = resourceService.getResource(id);
        if (response == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signature-key")
    public ResponseEntity<ResponseSignature> getSignature(@RequestBody RequestSignature request) {
        return ResponseEntity.ok().body(cloudinaryService.getSignature(request.getPublicId(), Tag.AUDIO));
    }

    @PostMapping("/create")
    public ResponseEntity<ResourceResponse> createResource(
            @RequestBody RequestCreateResource request) {
        ResourceResponse response = resourceService.createResource(request);
        if (response == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(response);
    }
}
