package com.spring.delivery.service.business.song;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestCreateSong;
import com.spring.delivery.domain.request.RequestUpdateSong;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.domain.response.ResponseSongCard;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SongService {
    ApiPaging<ResponseSongCard> getSongCard(Pageable pageable);

    ApiPaging<ResponseSongCard> getSongCardPopular(Pageable pageable);

    ResponseSong getSongDetail(Long id);

    void increaseViewCount(Long id);

    ApiPaging<ResponseSongCard> getLikedSongs(Pageable pageable);

    void likeSong(Long id);

    void unlikeSong(Long id);

    ResponseSong createSong(RequestCreateSong request);

    ResponseSong updateSong(Long id, RequestUpdateSong request);

    List<ResponseSongCard> getSongHistory(Long userId);

    List<ResponseSongCard> search(String name);
}
