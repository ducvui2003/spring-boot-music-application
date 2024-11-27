package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(target = "artist", source = "artist.name")
    @Mapping(target = "genre", source = "genre.name")
    @Mapping(target = "cover", source = "cover.url")
    @Mapping(target = "url", source = "source.url")
    ResponseSong toSongResponse(Song song);

    @Mapping(target = "artist", source = "artist.name")
    @Mapping(target = "thumbnail", source = "cover.url")
    @Mapping(target = "title", source = "title")
    ResponseSongCard toSongCardResponse(Song song);


}
