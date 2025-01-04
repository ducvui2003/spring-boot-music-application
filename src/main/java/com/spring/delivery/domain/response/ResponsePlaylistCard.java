/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:19 PM - 05/12/2024
 * User: kimin
 **/

package com.spring.delivery.domain.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponsePlaylistCard {
    Long id;
    String name;
    String description;
    String coverUrl;
    Integer totalSong;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
