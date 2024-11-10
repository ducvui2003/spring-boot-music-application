package com.spring.delivery.domain.request;

import java.time.Instant;

public record RequestCreateSong(
        String title,
        String artist,
        String album,
        String genre,
        Long resourceId,
        Long coverId,
        Instant createdAt,
        Instant updatedAt
        ) {
}
