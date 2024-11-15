package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseArtistCard;
import com.spring.delivery.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "avatar", source = "avatar.url")
    ResponseArtistCard toArtistCardResponse(Artist artist);
}
