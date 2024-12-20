package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCard;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistDetail;
import com.spring.delivery.service.business.playlist.PlaylistService;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/playlist")
public class PlaylistController {
    PlaylistService playlistService;

    @GetMapping()
    @ApiMessage("Get success")
    public ResponseEntity<ApiPaging<ResponsePlaylistCard>> getPlaylist(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok().body(playlistService.getPlayList(pageable));
    }

    @GetMapping("/{id}")
    @ApiMessage("Get success")
    public ResponseEntity<ResponsePlaylistDetail> getPlaylistDetail(@PathVariable("id") Long id, @PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok().body(playlistService.getPlayListDetail(id, pageable));
    }

    @PostMapping()
    @ApiMessage("Creat success")
    public ResponseEntity<ResponsePlaylistCreated> createPlaylist(@Valid @RequestBody RequestPlaylistCreated request) {
        return ResponseEntity.ok().body(playlistService.createPlaylist(request));
    }

    @PutMapping("/add/{id}/{songId}")
    @ApiMessage("Add song success")
    public ResponseEntity<Void> addSongToPlaylist(
            @PathVariable Long id, @PathVariable Long songId) {
        playlistService.addSongToPlaylist(id, songId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remove/{id}/{songId}")
    @ApiMessage("delete song success")
    public ResponseEntity<Void> removeSongToPlaylist(
            @PathVariable Long id, @PathVariable Long songId) {
        playlistService.removeSongFromPlaylist(id, songId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete success")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.ok().build();
    }
}
