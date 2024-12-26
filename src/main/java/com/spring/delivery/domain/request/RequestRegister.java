package com.spring.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.delivery.util.anotation.PasswordValid;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class RequestRegister {
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid phoneNumber address")
    String email;

    @NotBlank(message = "Full name is required")
    @JsonProperty("full_name")
    String fullName;


    @NotBlank(message = "Password is required")
    @PasswordValid(message = "Password is need at least 8 characters; 1 uppercase; 1 lowercase; 1 number and 1 special character")
    String password;

    @NotBlank(message = "Phone number is required")
    @JsonProperty("phone_number")
    String phoneNumber;

    boolean sex;

    @NotNull(message = "Birthday is required")
    LocalDate birthday;
}
