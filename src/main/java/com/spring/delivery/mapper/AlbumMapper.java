package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseAlbumCard;
import com.spring.delivery.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    @Mapping(target = "artist", source = "artist.name")
    @Mapping(target = "cover", source = "cover.url")
    ResponseAlbumCard toResponseAlbumCard(Album album);
}
