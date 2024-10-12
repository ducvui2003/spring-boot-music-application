package com.spring.delivery.controller;

import com.spring.delivery.domain.request.RequestUploadResource;
import com.spring.delivery.domain.response.ResourceResponse;
import com.spring.delivery.service.business.resource.ResourceService;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.enums.Tag;
import com.spring.delivery.util.validation.ValidationStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/upload")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceController {
    ResourceService resourceService;
    @Qualifier("validationAudio")
    ValidationStrategy validationAudio;
    @Qualifier("validationImage")
    ValidationStrategy validationImage;

    @PostMapping("/audio")
    public ResponseEntity<ResourceResponse> createAudio(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile multipart) {
        ResourceResponse response = resourceService.createResource(RequestUploadResource.builder()
                .parent(Tag.AUDIO.getName())
                .name(name)
                .tag(Tag.AUDIO)
                .multipartFile(multipart)
                .build(), validationAudio);
        if (response == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/image")
    public ResponseEntity<ResourceResponse> handleUploadImage(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile multipart,
            @RequestParam("tag") Tag tag) {
        ResourceResponse response = resourceService.createResource(RequestUploadResource.builder()
                .parent(Tag.SONG.getName())
                .name(name)
                .tag(tag)
                .multipartFile(multipart)
                .build(), validationImage);
        if (response == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(response);
    }
}
