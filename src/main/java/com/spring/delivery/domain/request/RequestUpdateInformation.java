package com.spring.delivery.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateInformation {
    @NotBlank
//    check validate(kiem tra các trường có bị null hay không)
    private String fullName;
    private String phoneNumber;
    private boolean sex;
    private LocalDate birthday;

}
