package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseAlbumCard;
import com.spring.delivery.domain.response.ResponseArtistCard;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.service.business.album.AlbumService;
import com.spring.delivery.service.business.artist.ArtistService;
import com.spring.delivery.service.business.song.SongService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/rank")
public class RankController {
    ArtistService artistService;
    SongService songService;
    AlbumService albumService;

    @GetMapping("/song/popular")
    public ResponseEntity<ApiPaging<ResponseSongCard>> getPopularMusic(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(songService.getSongCardPopular(pageable));
    }

    @GetMapping("/album/popular")
    public ResponseEntity<ApiPaging<ResponseAlbumCard>> getAlbumPopular(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(albumService.getAlbumCard(pageable));
    }

    @GetMapping("/artist/popular")
    public ResponseEntity<ApiPaging<ResponseArtistCard>> getArtistPopular(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(artistService.getArtistCard(pageable));
    }
}
