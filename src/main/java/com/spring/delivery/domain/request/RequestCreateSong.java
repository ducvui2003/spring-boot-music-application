package com.spring.delivery.domain.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestCreateSong {
    String title;
    String artist;
    String album;
    String genre;
    MultipartFile fileCover;
    MultipartFile fileSource;
}
