package com.spring.delivery.controller.admin;

import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.request.RequestCreateSong;
import com.spring.delivery.domain.request.RequestUpdateSong;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.service.business.song.SongService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/song")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminSongController {
    SongService songService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ResponseSong> createSong(@Valid @RequestBody RequestCreateSong request) {
        return ResponseEntity.ok().body(songService.createSong(request));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseSong> updateSong(@PathVariable("id") Long id, @Valid @RequestBody RequestUpdateSong request) {
        return ResponseEntity.ok().body(songService.updateSong(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable("id") Long id) {
        boolean isDelete = songService.deleteSong(id);
        if (isDelete) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
