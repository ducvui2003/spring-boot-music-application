package com.spring.delivery.service.business.song;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestCreateSong;
import com.spring.delivery.domain.request.RequestUpdateSong;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.mapper.SongMapper;
import com.spring.delivery.model.*;
import com.spring.delivery.repository.*;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.PageableUtil;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongServiceImpl implements SongService {
    SongRepository songRepository;
    SongMapper songMapper;
    SecurityUtil securityUtil;
    UserRepository userRepository;
    ArtistRepository artistRepository;
    AlbumRepository albumRepository;
    ResourceRepository resourceRepository;
    GenreRepository genreRepository;
    PageableUtil pageableUtil;
    CloudinaryService cloudinaryService;
    ListeningHistoryRepository listeningHistoryRepository;

    @Override
    public ApiPaging<ResponseSongCard> getSongCard(Pageable pageable) {
        Page<Song> page = songRepository.findAll(pageable);
        return pageableUtil.handlePaging(page, this::toSongResponseCard);
    }

    @Override
    public ApiPaging<ResponseSongCard> getSongCardPopular(Pageable pageable) {
        Page<Song> page = songRepository.findSongPopular(pageable);
        return pageableUtil.handlePaging(page, this::toSongResponseCard);
    }

    @Override
    public ResponseSong getSongDetail(Long id) {
        Optional<Song> song = songRepository.findById(id);
        ResponseAuthentication.UserDTO userDTO = securityUtil.getCurrentUserDTO().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (this.userRepository.findById(userDTO.id()).isEmpty()) {
            throw new AppException(AppErrorCode.USER_NOT_FOUND);
        }
        if (song.isEmpty()) {
            throw new AppException("Song not found");
        }
        listeningHistoryRepository.save(ListeningHistory.builder()
                .user(new User() {{
                    setId(userDTO.id());
                }})
                .song(song.get()).build());
        return this.toSongResponse(song.get());
    }

    @Override
    public void increaseViewCount(Long id) {
        int row = songRepository.incrementViewCount(id);
        if (row == 0) {
            throw new AppException("Song not found");
        }
    }

    @Override
    public ApiPaging<ResponseSongCard> getLikedSongs(Pageable pageable) {
        return null;
    }

    @Override
    public void likeSong(Long id) {
        ResponseAuthentication.UserDTO userDTO = securityUtil.getCurrentUserDTO().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (this.userRepository.findById(userDTO.id()).isEmpty()) {
            throw new AppException(AppErrorCode.USER_NOT_FOUND);
        }
        this.songRepository.addSongToFavoriteIfExists(userDTO.id(), id);
    }

    @Override
    public void unlikeSong(Long id) {
        ResponseAuthentication.UserDTO userDTO = securityUtil.getCurrentUserDTO().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (this.userRepository.findById(userDTO.id()).isEmpty()) {
            throw new AppException(AppErrorCode.USER_NOT_FOUND);
        }
        this.songRepository.removeSongFromFavoriteIfExists(userDTO.id(), id);
    }

    @Override
    public ResponseSong createSong(RequestCreateSong request) {
        Song song = new Song();
        song.setTitle(request.title());
        Artist artist = artistRepository.findByName(request.artist()).orElseThrow(() -> new AppException("Artist not found"));
        song.setArtist(artist);

        if (request.album() != null) {
            Album album = albumRepository.findByName(request.album()).orElseThrow(() -> new AppException("Album not found"));
            artist.setAlbums(Set.of(album));
        }
        Genre genre = genreRepository.findByName(request.genre()).orElseThrow(() -> new AppException("Genre not found"));
        song.setGenre(genre);
        Resource cover = resourceRepository.findById(request.coverId()).orElseThrow(() -> new AppException("Resource not found"));
        song.setCover(cover);
        Resource source = resourceRepository.findById(request.sourceId()).orElseThrow(() -> new AppException("Resource not found"));
        song.setSource(source);
        return this.toSongResponse(songRepository.save(song));
    }

    @Override
    public ResponseSong updateSong(Long id, RequestUpdateSong request) {
        Song song = songRepository.findById(id).orElseThrow(() -> new AppException("Song not found"));
        song.setTitle(request.title());
        song.setAlbum(albumRepository.findByName(request.album()).orElseThrow(() -> new AppException("Album not found")));
        song.setArtist(artistRepository.findByName(request.artist()).orElseThrow(() -> new AppException("Artist not found")));
        song.setGenre(genreRepository.findByName(request.genre()).orElseThrow(() -> new AppException("Genre not found")));
        song.setCover(resourceRepository.findById(request.coverId()).orElseThrow(() -> new AppException("Resource not found")));
        return this.toSongResponse(songRepository.save(song));
    }

    private ResponseSong toSongResponse(Song song) {
        ResponseAuthentication.UserDTO userDTO = securityUtil.getCurrentUserDTO().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (this.userRepository.findById(userDTO.id()).isEmpty()) {
            throw new AppException(AppErrorCode.USER_NOT_FOUND);
        }

        ResponseSong responseSong = songMapper.toSongResponse(song);
        responseSong.setLike(song.getUsers().stream().filter(it -> it.getId() == userDTO.id()).findFirst().isPresent());
        //        public-id -> HLS URLs
        if (song.getSource() != null) {
            String url = cloudinaryService.generateHLS(song.getSource().getPublicId());
            responseSong.setUrl(url);
        }
        if (song.getArtist().getAlbums() != null) {
            String url = cloudinaryService.generateImage(song.getCover().getPublicId());
            responseSong.setCover(url);
        }
        return responseSong;
    }

    private ResponseSongCard toSongResponseCard(Song song) {
        ResponseSongCard responseSong = songMapper.toSongCardResponse(song);
        String url = cloudinaryService.generateImage(song.getCover().getPublicId());
        responseSong.setCover(url);
        return responseSong;
    }
}
