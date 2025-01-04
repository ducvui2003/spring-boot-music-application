package com.spring.delivery.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.spring.delivery.domain.request.RequestCreateResource;
import com.spring.delivery.domain.request.RequestSignature;
import com.spring.delivery.domain.response.ResponseResource;
import com.spring.delivery.domain.response.ResponseSignature;
import com.spring.delivery.service.business.resource.ResourceService;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.service.cloudinary.ResponseCloudinaryUpload;
import com.spring.delivery.util.enums.Tag;
import io.netty.util.internal.ObjectUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resource")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceController {
    ResourceService resourceService;
    CloudinaryService cloudinaryService;


    @GetMapping("/{id}")
    public ResponseEntity<ResponseResource> getResource(@PathVariable("id") Long id) {
        ResponseResource response = resourceService.getResource(id);
        if (response == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResource(
            @RequestParam("file") MultipartFile file
    ) {
        CompletableFuture<ResponseCloudinaryUpload> videoUpload = cloudinaryService.upload(file, Tag.AUDIO);
        CompletableFuture.allOf(videoUpload).join();
        try {
            log.info("Upload video completed", videoUpload.join());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("File uploaded successfully");
    }
}
