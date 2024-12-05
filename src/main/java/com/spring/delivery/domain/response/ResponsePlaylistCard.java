/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:19 PM - 05/12/2024
 * User: kimin
 **/

package com.spring.delivery.domain.response;

public record ResponsePlaylistCard(
        Integer id,
        String name,
        String description,
        String coverUrl,
        Integer totalSong) {
}
