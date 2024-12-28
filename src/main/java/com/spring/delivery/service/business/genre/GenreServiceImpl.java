/**
 * Class: GenreServiceImpl
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.service.business.genre;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestGenreCreated;
import com.spring.delivery.domain.response.ResponseGenre;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.mapper.GenreMapper;
import com.spring.delivery.model.Genre;
import com.spring.delivery.model.Resource;
import com.spring.delivery.model.Song;
import com.spring.delivery.repository.GenreRepository;
import com.spring.delivery.repository.ResourceRepository;
import com.spring.delivery.repository.SongRepository;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.PageableUtil;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreServiceImpl implements GenreService {
    GenreRepository genreRepository;
    GenreMapper genreMapper = GenreMapper.INSTANCE;
    SongRepository songRepository;
    ResourceRepository resourceRepository;
    CloudinaryService cloudinaryService;
    PageableUtil pageableUtil;

    @Override
    public ApiPaging<ResponseGenre> getGenres(Pageable pageable) {
        Page<Genre> page = genreRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new AppException("No genres found");
        }
        return pageableUtil.handlePaging(page, this::toGenreResponse);
    }

    @Override
    public void createGenre(RequestGenreCreated request) {
        if (genreRepository.existsByName(request.name())) {
            throw new AppException("Genre already exists");
        }
        Set<Song> songs = songRepository.findAllByIdIn(request.songId());
        Optional<Resource> image = resourceRepository.findById(request.coverId());
        Genre genre = genreMapper.toGenre(request);
        genre.setSongs(songs);
        image.ifPresent(genre::setCover);
        genreRepository.save(genre);
    }

    @Override
    public ApiPaging<ResponseSongCard> getSongs(Pageable pageable) {
        return null;
    }

    private ResponseGenre toGenreResponse(Genre genre) {
        ResponseGenre response = genreMapper.toGenreResponse(genre);
        if (genre.getCover() != null) {
            String cover = cloudinaryService.generateImage(genre.getCover().getPublicId());
            response.setCover(cover);
        }
        return response;
    }
}
