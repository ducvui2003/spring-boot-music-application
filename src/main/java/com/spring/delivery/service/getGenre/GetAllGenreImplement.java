package com.spring.delivery.service.getGenre;

import com.spring.delivery.model.Genre;
import com.spring.delivery.repository.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetAllGenreImplement implements GetAllGenre{
    private GenreRepository genreRepository;
    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }
}
