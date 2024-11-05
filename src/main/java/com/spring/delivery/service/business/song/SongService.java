package com.spring.delivery.service.business.song;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseSong;
import org.springframework.data.domain.Pageable;

public interface SongService {
    ApiPaging<ResponseSong> getSongs(Pageable pageable);
}
