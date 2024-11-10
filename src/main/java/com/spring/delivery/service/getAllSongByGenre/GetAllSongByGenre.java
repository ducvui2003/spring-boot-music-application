package com.spring.delivery.service.getAllSongByGenre;

import com.spring.delivery.domain.request.RequestGetAllSongByGenre;
import com.spring.delivery.model.Song;

import java.util.List;

public interface GetAllSongByGenre {
    public List<Song> getAllSongByGenre(RequestGetAllSongByGenre genre);
}
