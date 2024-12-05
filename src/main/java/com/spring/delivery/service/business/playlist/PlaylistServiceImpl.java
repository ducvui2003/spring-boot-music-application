package com.spring.delivery.service.business.playlist;

import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.mapper.PlayListMapper;
import com.spring.delivery.model.Playlist;
import com.spring.delivery.model.Song;
import com.spring.delivery.repository.PlaylistRepository;
import com.spring.delivery.repository.ResourceRepository;
import com.spring.delivery.repository.SongRepository;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PlaylistServiceImpl implements PlaylistService {
    PlaylistRepository playlistRepository;
    PlayListMapper playListMapper;
    ResourceRepository resourceRepository;
    SongRepository songRepository;
    UserRepository userRepository;
    CloudinaryService cloudinaryService;

    @Override
    public ResponsePlaylistCreated createPlaylist(RequestPlaylistCreated request, String email) {
        if (playlistRepository.existsByName(request.name())) {
            log.error("Playlist with name {} already exists", request.name());
            throw new AppException(AppErrorCode.EXIST);
        }
        if (!resourceRepository.existsById(request.coverId())) {
            log.error("Resource with id {} not found", request.coverId());
            throw new AppException(AppErrorCode.NOT_FOUND);
        }

        Playlist playlist = playListMapper.toPlaylist(request);
        Set<Song> songs = songRepository.findAllByIdIn(request.songIds());
        playlist.setSongs(songs);
        playlist.setUser(userRepository.findByEmail(email).orElseThrow(() -> {
            log.error("User with id {} not found", email);
            throw new AppException(AppErrorCode.NOT_FOUND);
        }));
        playlist = playlistRepository.save(playlist);

        return this.toResponsePlaylistCreated(playlist);
    }

    @Override
    public void deletePlaylist(Long id, String email) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> {
            log.error("Playlist with id {} not found", id);
            return new AppException(AppErrorCode.NOT_FOUND);
        });
        if (!playlist.getUser().getEmail().equals(email)) {
            throw new AppException(AppErrorCode.FORBIDDEN);
        }
        playlistRepository.deleteById(id);
    }

    @Override
    public void addSongToPlaylist(Long id, Long songId, String email) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> {
            log.error("Playlist with id {} not found", id);
            throw new AppException(AppErrorCode.NOT_FOUND);
        });

        playlist.setUser(userRepository.findByEmail(email).orElseThrow(() -> {
            log.error("User with id {} not found", email);
            throw new AppException(AppErrorCode.NOT_FOUND);
        }));

        Song song = songRepository.findById(songId).orElseThrow(() -> {
            log.error("Song with id {} not found", songId);
            throw new AppException(AppErrorCode.NOT_FOUND);
        });

        if (playlist.getSongs().contains(song)) {
            log.error("Song with id {} already exists in playlist with id {}", songId, id);
            throw new AppException(AppErrorCode.EXIST);
        }


        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

    @Override
    public void removeSongFromPlaylist(Long id, Long songId, String email) {


        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> {
            log.error("Playlist with id {} not found", id);
            throw new AppException(AppErrorCode.NOT_FOUND);
        });

        if (!playlist.getUser().getEmail().equals(email)) {
            throw new AppException(AppErrorCode.FORBIDDEN);
        }

        Song song = songRepository.findById(songId).orElseThrow(() -> {
            log.error("Song with id {} not found", songId);
            throw new AppException(AppErrorCode.NOT_FOUND);
        });

        if (!playlist.getSongs().contains(song)) {
            log.error("Song with id {} already exists in playlist with id {}", songId, id);
            throw new AppException(AppErrorCode.EXIST);
        }

        playlist.getSongs().remove(song);
        if (playlist.getSongs().isEmpty()) {
            playlistRepository.deleteById(id);
            return;
        }
        playlistRepository.save(playlist);
    }

    private ResponsePlaylistCreated toResponsePlaylistCreated(Playlist entity) {
        ResponsePlaylistCreated response = playListMapper.toResponsePlaylistCreated(entity);
        if (entity.getCover() != null) {
            response.setCover(cloudinaryService.generateImage(entity.getCover().getPublicId()));
        }
        return response;
    }
}
