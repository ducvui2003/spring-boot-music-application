package com.spring.delivery.domain.request;

public record RequestPlaylistCreated(
        String name,
        String description,
        Boolean isPublic
) {
}
