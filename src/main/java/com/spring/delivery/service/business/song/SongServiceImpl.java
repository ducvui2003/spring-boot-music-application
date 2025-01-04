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
import com.spring.delivery.service.business.resource.ResourceService;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.service.cloudinary.ResponseCloudinaryUpload;
import com.spring.delivery.util.PageableUtil;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.enums.Tag;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
    FavoriteRepository favoriteRepository;

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
        Optional<Song> optionalSong = songRepository.findById(id);
        ResponseAuthentication.UserDTO userDTO = securityUtil.getCurrentUserDTO().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (this.userRepository.findById(userDTO.id()).isEmpty()) {
            throw new AppException(AppErrorCode.USER_NOT_FOUND);
        }
        if (optionalSong.isEmpty()) {
            throw new AppException("Song not found");
        }
        var song = optionalSong.get();
        song.setViews(song.getViews().add(BigInteger.valueOf(1)));
        this.songRepository.save(song);
        addSongHistory(userDTO.id(), song);
        return this.toSongResponse(song);
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
        var song = new Favorite();
        song.setUser(new User() {{
            setId(userDTO.id());
        }});
        song.setSong(new Song() {{
            setId(id);
        }});
        if (favoriteRepository.existsBySong_Id(id)) return;
        this.favoriteRepository.save(song);
    }

    @Override
    public void unlikeSong(Long id) {
        ResponseAuthentication.UserDTO userDTO = securityUtil.getCurrentUserDTO().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (this.userRepository.findById(userDTO.id()).isEmpty()) {
            throw new AppException(AppErrorCode.USER_NOT_FOUND);
        }
        this.favoriteRepository.removeBySong_IdAndUser_Id(id, userDTO.id());
    }

    @Transactional
    @Override
    public void createSong(RequestCreateSong request) {
        Song song = new Song();
        if (songRepository.existsByTitle(request.getTitle())) {
            throw new AppException(AppErrorCode.SONG_EXISTED);
        }
        song.setTitle(request.getTitle());

        Artist artist = artistRepository.findByName(request.getArtist()).orElseThrow(() -> new AppException("Artist not found"));
        song.setArtist(artist);

        if (!request.getAlbum().isBlank()) {
            Album album = albumRepository.findByName(request.getAlbum()).orElseThrow(() -> new AppException("Album not found"));
            artist.setAlbums(Set.of(album));
        }
        if (!request.getGenre().isBlank()) {
            Genre genre = genreRepository.findByName(request.getGenre()).orElseThrow(() -> new AppException("Genre not found"));
            song.setGenre(genre);
        }

        CompletableFuture<ResponseCloudinaryUpload> audioUpload = cloudinaryService.upload(request.getFileSource(), Tag.AUDIO);
        CompletableFuture<ResponseCloudinaryUpload> coverUpload = cloudinaryService.upload(request.getFileSource(), Tag.SONG);
        CompletableFuture.allOf(audioUpload, coverUpload).join();

        try {
            ResponseCloudinaryUpload audioUploadData = audioUpload.get();
            Resource audioResource = Resource.builder()
                    .name(extractName(audioUploadData.getPublicId()))
                    .publicId(audioUploadData.getPublicId())
                    .tag(Tag.AUDIO)
                    .build();
            audioResource = resourceRepository.save(audioResource);
            song.setSource(audioResource);

            ResponseCloudinaryUpload coverUploadData = coverUpload.get();

            Resource coverResource = Resource.builder()
                    .name(extractName(coverUploadData.getPublicId()))
                    .publicId(coverUploadData.getPublicId())
                    .tag(Tag.SONG)
                    .build();
            coverResource = resourceRepository.save(coverResource);
            song.setCover(coverResource);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new AppException(AppErrorCode.SONG_CREATION_FAILED);
        }
        songRepository.save(song);
    }

    private String extractName(String publicId) {
        return publicId.substring(publicId.lastIndexOf("/") + 1);
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
        responseSong.setLike(favoriteRepository.findBySong_IdAndUser_Id(song.getId(), userDTO.id()).isPresent());
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


    @Override
    public List<ResponseSongCard> getSongHistory(Long userId) {
        List<ListeningHistory> histories = listeningHistoryRepository.findByUserId(userId);
        List<Long> songIds = histories.stream().map(item -> item.getSong().getId()).collect(Collectors.toList());
        Set<Song> songs = songRepository.findAllByIdIn(songIds);
        return songs.stream().map(this::toSongResponseCard).collect(Collectors.toList());
    }

    @Override
    public List<ResponseSongCard> search(String name) {
        var result = songRepository.findAllByTitleLike("%" + name + "%").stream().map(this::toSongResponseCard).collect(Collectors.toCollection(ArrayList::new));
        result.addAll(songRepository.findAllByArtist_NameLike("%" + name + "%").stream().map(this::toSongResponseCard).toList());
        return result;
    }

    private void addSongHistory(Long userId, Song song) {
        ListeningHistory listeningHistory = new ListeningHistory();
        listeningHistory.setSong(song);
        listeningHistory.setUser(userRepository.findById(userId).orElseThrow(() -> new AppException("User not found")));
        listeningHistoryRepository.save(listeningHistory);
    }

    @Override
    public boolean deleteSong(Long id) {
        return false;
    }
}
