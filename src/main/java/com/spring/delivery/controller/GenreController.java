/**
 * Class: GerneController
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.response.ResponseGenre;
import com.spring.delivery.service.business.genre.GenreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreController {
    GenreService genreService;

    @GetMapping
    public ResponseEntity<ApiResponse<ApiPaging<ResponseGenre>>> getGenres(@RequestParam(defaultValue = "1") int page,
                                                                           @RequestParam(defaultValue = "10") int size,
                                                                           @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        return ResponseEntity.ok(ApiResponse.<ApiPaging<ResponseGenre>>builder()
                .data(genreService.getGenres(pageable))
                .build());
    }
}
