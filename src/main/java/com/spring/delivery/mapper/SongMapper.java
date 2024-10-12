package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SongMapper {
    static final SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "artist", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "cover", source = "cover.url")
    ResponseSong toSongResponse(Song song);
}
