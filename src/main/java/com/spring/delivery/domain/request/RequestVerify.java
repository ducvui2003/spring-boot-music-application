package com.spring.delivery.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestVerify(
        @NotBlank(message = "OTP is required")
        String otp
) {
}
