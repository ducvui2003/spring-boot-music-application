package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCard;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.model.JwtPayload;
import com.spring.delivery.service.business.playlist.PlaylistService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/playlist")
public class PlaylistController {
    PlaylistService playlistService;
    SecurityUtil securityUtil;

    @GetMapping()
    @ApiMessage("Get success")
    public ResponseEntity<ApiPaging<ResponsePlaylistCard>> getPlaylist(@PageableDefault(size = 10, sort = "id", page = 0) Pageable pageable) {
        return ResponseEntity.ok().body(playlistService.getPlayList(pageable));
    }

    @PostMapping()
    @ApiMessage("Creat success")
    public ResponseEntity<ResponsePlaylistCreated> createPlaylist(@Valid @RequestBody RequestPlaylistCreated request) {
        return ResponseEntity.ok().body(playlistService.createPlaylist(request));
    }

    @PutMapping("/add/{id}/{songId}")
    public ResponseEntity<Void> addSongToPlaylist(
            @PathVariable Long id, @PathVariable Long songId) {
        String email = securityUtil.getCurrentUserDTO().get().email();
        playlistService.addSongToPlaylist(id, songId, email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remove/{id}/{songId}")
    public ResponseEntity<Void> removeSongToPlaylist(
            @PathVariable Long id, @PathVariable Long songId) {
        String email = securityUtil.getCurrentUserDTO().get().email();
        playlistService.removeSongFromPlaylist(id, songId, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete success")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        String email = securityUtil.getCurrentUserDTO().get().email();
        playlistService.deletePlaylist(id, email);
        return ResponseEntity.ok().build();
    }
}
