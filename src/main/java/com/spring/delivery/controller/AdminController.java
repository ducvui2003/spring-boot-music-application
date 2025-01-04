package com.spring.delivery.controller;

import com.spring.delivery.domain.request.RequestCreateSong;
import com.spring.delivery.domain.request.RequestUpdateSong;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.service.business.album.AlbumService;
import com.spring.delivery.service.business.artist.ArtistService;
import com.spring.delivery.service.business.genre.GenreService;
import com.spring.delivery.service.business.song.SongService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/v1/admin")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    SongService songService;
    AlbumService albumService;
    ArtistService artistService;
    GenreService genreService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/song/create")
    public ResponseEntity<Void> createSong(
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("album") String album,
            @RequestParam("genre") String genre,
            @RequestParam("file_cover") MultipartFile fileCover,
            @RequestParam("file_source") MultipartFile fileSource
    ) {
        RequestCreateSong request = RequestCreateSong.builder()
                .title(title)
                .artist(artist)
                .album(album)
                .genre(genre)
                .fileCover(fileCover)
                .fileSource(fileSource)
                .build();
        songService.createSong(request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/song/update/{id}")
    public ResponseEntity<ResponseSong> updateSong(@PathVariable("id") Long id, @Valid @RequestBody RequestUpdateSong request) {
        return ResponseEntity.ok().body(songService.updateSong(id, request));
    }

    @DeleteMapping("/song/delete/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable("id") Long id) {
        boolean isDelete = songService.deleteSong(id);
        if (isDelete) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/album")
    public ResponseEntity<List<String>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbumName());
    }

    @GetMapping("/artist")
    public ResponseEntity<List<String>> getAllArtist() {
        return ResponseEntity.ok(artistService.getAllArtistName());
    }

    @GetMapping("/genre")
    public ResponseEntity<List<String>> getAllGenre() {
        return ResponseEntity.ok(genreService.getGenreName());
    }
}
