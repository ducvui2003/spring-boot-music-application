package com.spring.delivery.controller;

import com.spring.delivery.domain.request.HLSCloudinaryRequest;
import com.spring.delivery.domain.response.ResponseVideoCloudinary;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.enums.Folder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HLSController {
    CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseVideoCloudinary> handleUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile multipart) throws Exception {
        ResponseVideoCloudinary response = cloudinaryService.uploadMp3(HLSCloudinaryRequest.builder()
                .parent(Folder.SONG.getName())
                .name(name)
                .mp3(multipart)
                .build());
        return ResponseEntity.ok().body(response);
    }
}
