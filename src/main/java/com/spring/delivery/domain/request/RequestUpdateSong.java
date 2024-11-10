package com.spring.delivery.domain.request;

public record RequestUpdateSong
        (
                Long id,
                String title,
                String artist,
                String album,
                String genre,
                Long coverId
        ) {
}
