package com.spring.delivery.domain.response;

import com.spring.delivery.domain.ApiPaging;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAlbumDetail extends ResponseAlbumCard {
    ApiPaging<ResponseSongCard> songs;
}
