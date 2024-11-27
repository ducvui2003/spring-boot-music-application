package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestCreateSong;
import com.spring.delivery.domain.request.RequestUpdateSong;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.service.business.song.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/song")
public class SongController {
    SongService songService;

    @GetMapping("/list")
    public ResponseEntity<ApiPaging<ResponseSongCard>> getSongs(@PageableDefault(size = 10, sort = "id", page = 0) Pageable pageable) {
        ApiPaging<ResponseSongCard> songs = songService.getSongCard(pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseSong> getSongDetail(@PathVariable(name = "id") Long id) {
        ResponseSong song = songService.getSongDetail(id);
        return ResponseEntity.ok(song);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Void> increaseViewCount(@PathVariable("id") Long id) {
        songService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/liked")
    public ResponseEntity<ApiPaging<ResponseSongCard>> getLikedSongs(@PageableDefault(size = 10, sort = "id", page = 0) Pageable pageable) {
        ApiPaging<ResponseSongCard> songs = songService.getLikedSongs(pageable);
        return ResponseEntity.ok(songs);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Void> likeSong(@PathVariable("id") Long id) {
        songService.likeSong(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unlike/{id}")
    public ResponseEntity<Void> unlikeSong(@PathVariable("id") Long id) {
        songService.unlikeSong(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseSong> createSong(@Valid @RequestBody RequestCreateSong request) {
        return ResponseEntity.ok().body(songService.createSong(request));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseSong> updateSong(@PathVariable("id") Long id, @Valid @RequestBody RequestUpdateSong request) {
        return ResponseEntity.ok().body(songService.updateSong(id, request));
    }

}
