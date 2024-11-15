package com.spring.delivery.domain.request;

public record FilterProductController(
        long page,
        int limit
) {
}
