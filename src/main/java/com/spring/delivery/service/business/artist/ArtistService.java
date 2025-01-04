package com.spring.delivery.service.business.artist;


import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseArtistCard;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArtistService {
    ApiPaging<ResponseArtistCard> getArtistCard(Pageable pageable);

    List<ResponseArtistCard> findByName(String name);
}
