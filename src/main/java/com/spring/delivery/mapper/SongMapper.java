package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "artist", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "cover", source = "cover.url")
    ResponseSong toSongResponse(Song song);

    @Mapping(target = "artist", source = "artist.name")
    @Mapping(target = "thumbnail", source = "cover.url")
    @Mapping(target = "title", source = "title")
    ResponseSongCard toSongCardResponse(Song song);
}
