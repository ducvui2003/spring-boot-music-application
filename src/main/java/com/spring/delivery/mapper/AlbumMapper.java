package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseAlbumCard;
import com.spring.delivery.domain.response.ResponseAlbumDetail;
import com.spring.delivery.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    @Mapping(target = "artist", source = "artist.name")
    @Mapping(target = "coverUrl", ignore = true)
    ResponseAlbumCard toResponseAlbumCard(Album album);

    @Mapping(target = "artist", source = "artist.name")
    @Mapping(target = "coverUrl", ignore = true)
    @Mapping(target = "songs", ignore = true)
    ResponseAlbumDetail toAlbumDetailResponse(Album album);
}
