package com.spring.delivery.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestVerify(
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid phoneNumber address")
        String email,
        @NotBlank(message = "OTP is required")
        String otp
) {
}
