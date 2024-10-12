/**
 * Class: GenreServiceImpl
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.service.business.genre;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseGenre;
import com.spring.delivery.mapper.GenreMapper;
import com.spring.delivery.model.Genre;
import com.spring.delivery.repository.GenreRepository;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreServiceImpl implements GenreService {
    GenreRepository genreRepository;
    GenreMapper genreMapper = GenreMapper.INSTANCE;

    @Override
    public ApiPaging<ResponseGenre> getGenres(Pageable pageable) {
        Page<Genre> page = genreRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new AppException("No genres found");
        }
        List<ResponseGenre> data = page.getContent().stream()
                .map(genreMapper::toGenreResponse)
                .toList();
        return ApiPaging.<ResponseGenre>builder()
                .size(page.getSize())
                .currentPage(page.getNumber())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .content(data)
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
