package com.spring.delivery.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInformation {
    private String fullName;
    private String phoneNumber;
    private boolean sex;
    private Date birthDay;

}
