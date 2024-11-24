package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Template {
    WELL_COME(9),
    RESET_PASSWORD(10),
    VERIFY_ACCOUNT(8);
    private long value;

}
