package com.spring.delivery.domain.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseSong {
    String id;
    String title;
    String cover;
    String artist;
    String url;
    String genre;
    Instant createdAt;
    Instant updatedAt;
}
