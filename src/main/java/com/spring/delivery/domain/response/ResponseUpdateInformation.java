package com.spring.delivery.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUpdateInformation {
    private String fullName;
    private String phoneNumber;
    private boolean sex;
    private Date birthday;
}
