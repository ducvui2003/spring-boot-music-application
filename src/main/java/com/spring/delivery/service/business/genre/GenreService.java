/**
 * Class: GenreService
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.service.business.genre;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestGenreCreated;
import com.spring.delivery.domain.response.ResponseGenre;
import com.spring.delivery.domain.response.ResponseSongCard;
import org.springframework.data.domain.Pageable;


public interface GenreService {
    ApiPaging<ResponseGenre> getGenres(Pageable pageable);

    void createGenre(RequestGenreCreated request);

    ApiPaging<ResponseSongCard> getSongs(Pageable pageable);

}
