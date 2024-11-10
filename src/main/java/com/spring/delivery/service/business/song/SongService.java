package com.spring.delivery.service.business.song;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestCreateSong;
import com.spring.delivery.domain.response.ResponseCreateSong;
import com.spring.delivery.domain.request.RequestCreateSong;
import com.spring.delivery.domain.response.ResponseCreateSong;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.domain.response.ResponseSongCard;
import org.springframework.data.domain.Pageable;

public interface SongService {
    ApiPaging<ResponseSongCard> getSongCard(Pageable pageable);

    ResponseSong getSongDetail(Long id);

    void increaseViewCount(Long id);


    ApiPaging<ResponseSongCard> getLikedSongs(Pageable pageable);

    void likeSong(Long id);

    void unlikeSong(Long id);

    ResponseCreateSong createSong(RequestCreateSong request);

}
