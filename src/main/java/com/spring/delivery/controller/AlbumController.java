/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 7:29 AM - 27/12/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.controller;

import com.spring.delivery.domain.response.ResponseAlbumDetail;
import com.spring.delivery.service.business.album.AlbumService;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/album")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlbumController {
    AlbumService albumService;

    @GetMapping("/{id}")
    @ApiMessage("Get success")
    public ResponseEntity<ResponseAlbumDetail> getDetail(@PathVariable("id") Long id, @PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok().body(albumService.getDetail(id, pageable));
    }
}
