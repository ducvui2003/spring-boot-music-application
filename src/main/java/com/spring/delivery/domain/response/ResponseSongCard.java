package com.spring.delivery.domain.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSongCard {
    Long id;
    String cover;
    String title;
    String artist;
    String genre;
    BigInteger views;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
