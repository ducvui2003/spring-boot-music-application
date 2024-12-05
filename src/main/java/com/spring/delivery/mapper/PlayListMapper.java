package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.model.Playlist;
import com.spring.delivery.model.Resource;
import com.spring.delivery.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PlayListMapper {
    Playlist toPlaylist(RequestPlaylistCreated request);

    @Mapping(source = "songs", target = "songIds", qualifiedByName = "toSongIds")
    @Mapping(target = "cover", ignore = true)
    ResponsePlaylistCreated toResponsePlaylistCreated(Playlist entity);

    @Named("toSongIds")
    default List<Long> toSongs(Set<Song> songs) {
        return songs.stream().map(Song::getId).toList();
    }

}
