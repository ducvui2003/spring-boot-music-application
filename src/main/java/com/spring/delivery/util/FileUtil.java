package com.spring.delivery.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class FileUtil {
    public File moveFile(MultipartFile multipartFile, String destination) throws IOException {
        // Ensure the destination directory exists
        File destDir = new File(destination);
        if (!destDir.exists()) {
            destDir.mkdirs(); // Create directories if they don't exist
        }

        // Create a temporary file
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        Path tempFilePath = Files.createTempFile(destDir.toPath(), "temp_", extension);
        File tempFile = tempFilePath.toFile();

        // Transfer the uploaded file to the temp file
        multipartFile.transferTo(tempFilePath);

        return tempFile;
    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
