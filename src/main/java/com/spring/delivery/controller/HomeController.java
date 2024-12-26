package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.*;
import com.spring.delivery.service.business.album.AlbumService;
import com.spring.delivery.service.business.artist.ArtistService;
import com.spring.delivery.service.business.playlist.PlaylistService;
import com.spring.delivery.service.business.song.SongService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import feign.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HomeController {
    SecurityUtil securityUtil;
    SongService songService;
    AlbumService albumService;
    PlaylistService playlistService;

    @GetMapping("/playlist")
    public ResponseEntity<List<ResponsePlaylistCard>> getPlayList() {
        PageRequest pageable = PageRequest.of(0, 5);
        Optional<ResponseAuthentication.UserDTO> userDTOOptional = securityUtil.getCurrentUserDTO();
        List<ResponsePlaylistCard> data;
        if (userDTOOptional.isPresent()) {
            data = playlistService.getPlayList(pageable).getContent();
        } else {
            data = playlistService.getPlayListNonAuth(pageable).getContent();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/mixes")
    public ResponseEntity<List<ResponseAlbumCard>> getAlbumPopular() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<ResponseAlbumCard> data = albumService.getAlbumCard(pageRequest).getContent();
        return ResponseEntity.ok(data);
    }


    @GetMapping("/recent")
    public ResponseEntity<List<ResponseSongCard>> getRecentSongs() {
        ResponseAuthentication.UserDTO userDTO = securityUtil.getCurrentUserDTO().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        List<ResponseSongCard> data = songService.getSongHistory(userDTO.id());
        return ResponseEntity.ok(data);
    }
}
