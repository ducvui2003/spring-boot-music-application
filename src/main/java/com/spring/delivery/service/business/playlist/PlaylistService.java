package com.spring.delivery.service.business.playlist;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCard;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistDetail;
import com.spring.delivery.domain.response.ResponseSongCard;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {

    ResponsePlaylistCreated createPlaylist(RequestPlaylistCreated request);

    void deletePlaylist(Long id);

    void addSongToPlaylist(Long id, Long songId);

    void removeSongFromPlaylist(Long id, Long songId);

    ApiPaging<ResponsePlaylistCard> getPlayList(Pageable pageable);


    ApiPaging<ResponsePlaylistCard> getPlayListNonAuth(Pageable pageable);

    ResponsePlaylistDetail getPlayListDetail(Long id, Pageable pageable);
}
