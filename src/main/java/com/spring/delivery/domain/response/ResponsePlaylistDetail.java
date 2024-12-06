/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:19 PM - 05/12/2024
 * User: kimin
 **/

package com.spring.delivery.domain.response;

import com.spring.delivery.domain.ApiPaging;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponsePlaylistDetail extends ResponsePlaylistCard {
    ApiPaging<ResponseSongCard> songs;
}
