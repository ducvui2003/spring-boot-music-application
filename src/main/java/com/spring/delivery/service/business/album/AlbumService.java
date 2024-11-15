package com.spring.delivery.service.business.album;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseAlbumCard;
import org.springframework.data.domain.Pageable;

public interface AlbumService {
    ApiPaging<ResponseAlbumCard> getAlbumCard(Pageable pageable);

}
