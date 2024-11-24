/**
 * Class: GerneController
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.request.RequestGenreCreated;
import com.spring.delivery.domain.request.RequestGetAllSongByGenre;
import com.spring.delivery.domain.response.ResponseGenre;
import com.spring.delivery.model.Genre;
import com.spring.delivery.model.Song;
import com.spring.delivery.service.business.genre.GenreService;
import com.spring.delivery.service.business.genre.GenreServiceImpl;
import com.spring.delivery.service.getAllSongByGenre.GetAllSongByGenreImplement;
import com.spring.delivery.service.getGenre.GetAllGenreImplement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreController {
    GenreService genreService;
    GetAllGenreImplement getAllGenreImplement;
    GetAllSongByGenreImplement getAllSongByGenreImplement;

    @GetMapping
    public ResponseEntity<ApiResponse<ApiPaging<ResponseGenre>>> getGenres(@RequestParam(defaultValue = "1") int page,
                                                                           @RequestParam(defaultValue = "10") int size,
                                                                           @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        return ResponseEntity.ok(ApiResponse.<ApiPaging<ResponseGenre>>builder()
                .data(genreService.getGenres(pageable))
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createGenre(@RequestBody RequestGenreCreated request) {
        genreService.createGenre(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Genre created successfully")
                        .build()
        );
    }

    @GetMapping("/get-all-genres")
    public ResponseEntity<List<Genre>> getAllGenre() {
        List<Genre> genres = getAllGenreImplement.getAllGenre();
        if (genres.isEmpty()) {
//          return 204 nếu không có  genres
            return ResponseEntity.noContent().build();
        }
//      return 200 có genres
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/get-all-song-by-genre")
    public ResponseEntity<List<Song>> getAllSongByGenre(RequestGetAllSongByGenre requestGetAllSongByGenre) {
        List<Song> songs = getAllSongByGenreImplement.getAllSongByGenre(requestGetAllSongByGenre);
        return ResponseEntity.ok(songs);
    }
}
