package com.spring.delivery.controller;

import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.model.JwtPayload;
import com.spring.delivery.service.business.playlist.PlaylistService;
import com.spring.delivery.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/create")
    public ResponseEntity<ResponsePlaylistCreated> createPlaylist(@AuthenticationPrincipal JwtPayload userDetails,
                                                                  @Valid @RequestBody RequestPlaylistCreated request) {
        log.info("User {} creating playlist", SecurityUtil.getCurrentUserLogin());
        String email = securityUtil.getCurrentUserDTO().get().email();
        return ResponseEntity.ok().body(playlistService.createPlaylist(request, email));
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        String email = securityUtil.getCurrentUserDTO().get().email();
        playlistService.deletePlaylist(id, email);
        return ResponseEntity.ok().build();
    }
}
