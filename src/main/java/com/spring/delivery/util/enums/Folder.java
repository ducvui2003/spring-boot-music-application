package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Folder {
    SONG("songs"),

    GENRE("genre");

    private String name;

}