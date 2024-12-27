package com.spring.delivery.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAlbumCard {
    long id;
    String name;
    String artist;
    String coverUrl;
    Instant releaseDate;
}
