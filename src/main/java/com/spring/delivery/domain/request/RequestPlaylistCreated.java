package com.spring.delivery.domain.request;

import java.util.List;

public record RequestPlaylistCreated(
        String name,
        String description,
        boolean isPubic,
        List<Long> songIds,
        Long coverId
) {
}
