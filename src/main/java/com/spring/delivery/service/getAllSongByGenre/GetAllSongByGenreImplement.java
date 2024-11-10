package com.spring.delivery.service.getAllSongByGenre;

import com.spring.delivery.domain.request.RequestGetAllSongByGenre;
import com.spring.delivery.model.Song;
import com.spring.delivery.repository.SongRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetAllSongByGenreImplement implements GetAllSongByGenre {
    private SongRepository songRepository;

    @Override
    public List<Song> getAllSongByGenre(RequestGetAllSongByGenre genre) {
        List<Song> listSong = songRepository.findAll();
        List<Song> newListSong = new ArrayList<>();
        for (Song song: listSong) {
            if(song.getGenre().getName().equals(genre.getName())){
                newListSong = (List<Song>) song;
            } else if (song.getGenre().getDescription().equals(genre.getDescription())) {
                newListSong = (List<Song>) song;
            }
        }
        return newListSong;
    }
}
