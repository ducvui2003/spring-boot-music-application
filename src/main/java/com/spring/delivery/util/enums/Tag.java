package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Tag {
    USER("image/user"),
    SONG("image/song"),
    GENRE("image/genre"),
    ARTIST("image/artist"),
    ALBUM("image/album"),
    AUDIO("audio"),
    ;
    private String name;
}
