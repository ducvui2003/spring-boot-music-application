package com.spring.delivery.service.business.playlist;

import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;

public interface PlaylistService {

    ResponsePlaylistCreated createPlaylist(RequestPlaylistCreated request, String email);

    void deletePlaylist(Long id, String email);

    void addSongToPlaylist(Long id, Long songId, String email);

    void removeSongFromPlaylist(Long id, Long songId, String email);
}
