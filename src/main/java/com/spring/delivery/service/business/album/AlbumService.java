package com.spring.delivery.service.business.album;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseAlbumCard;
import com.spring.delivery.domain.response.ResponseAlbumDetail;
import org.springframework.data.domain.Pageable;

public interface AlbumService {
    ApiPaging<ResponseAlbumCard> getAlbumCard(Pageable pageable);

    ResponseAlbumDetail getDetail(Long id, Pageable pageable);
}
