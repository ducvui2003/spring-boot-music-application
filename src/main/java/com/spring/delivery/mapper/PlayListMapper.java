package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCard;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistDetail;
import com.spring.delivery.model.Playlist;
import com.spring.delivery.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PlayListMapper {
    Playlist toPlaylist(RequestPlaylistCreated request);

    @Mapping(target = "cover", ignore = true)
    ResponsePlaylistCreated toResponsePlaylistCreated(Playlist entity);

    @Mapping(source = "songs", target = "totalSong", qualifiedByName = "toTotalSong")
    ResponsePlaylistCard toPlaylistCardResponse(Playlist playlist);

    @Mapping(source = "songs", target = "totalSong", qualifiedByName = "toTotalSong")
    @Mapping(target = "songs", ignore = true)
    ResponsePlaylistDetail toPlaylistDetailResponse(Playlist playlist);

    @Named("toTotalSong")
    default Integer totalSong(Set<Song> songs) {
        return songs == null ? 0 : songs.size();
    }
}
