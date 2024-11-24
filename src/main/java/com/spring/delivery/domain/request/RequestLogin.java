package com.spring.delivery.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RequestLogin(
        @NotBlank
        @Email
        String email,

        @NotBlank(message = "Password is required")
        String password
) {

}
