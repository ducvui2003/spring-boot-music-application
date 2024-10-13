package com.spring.delivery.controller;

import com.spring.delivery.domain.request.RequestUploadResource;
import com.spring.delivery.domain.response.ResourceResponse;
import com.spring.delivery.service.business.resource.ResourceService;
import com.spring.delivery.util.enums.Tag;
import com.spring.delivery.util.validation.ValidationStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resource")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceController {
    ResourceService resourceService;
    @Qualifier("validationAudio")
    ValidationStrategy validationAudio;
    @Qualifier("validationImage")
    ValidationStrategy validationImage;


    @GetMapping("/audio/{id}")
    public ResponseEntity<ResourceResponse> getAudio(@PathVariable("id") Long id) {
        ResourceResponse response = resourceService.getResource(id);
        if (response == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/upload/audio")
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
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/upload/image")
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
