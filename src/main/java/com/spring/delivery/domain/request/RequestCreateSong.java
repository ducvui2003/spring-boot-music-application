package com.spring.delivery.domain.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCreateSong(
        @NotBlank
        String title,
        @NotBlank
        String artist,
        String album,
        @NotBlank
        String genre,
        @NotNull
        Long sourceId,
        @NotNull
        Long coverId
) {
}
