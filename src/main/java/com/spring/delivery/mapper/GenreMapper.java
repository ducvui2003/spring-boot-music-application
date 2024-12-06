/**
 * Class: GerneMapper
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.RequestGenreCreated;
import com.spring.delivery.domain.response.ResponseGenre;
import com.spring.delivery.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    @Mapping(target = "cover", ignore = true)
    ResponseGenre toGenreResponse(Genre genre);

    @Mapping(target = "cover", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Genre toGenre(RequestGenreCreated request);
}
