package com.spring.delivery.service.business.playlist;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestPlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistCard;
import com.spring.delivery.domain.response.ResponsePlaylistCreated;
import com.spring.delivery.domain.response.ResponsePlaylistDetail;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.mapper.PlayListMapper;
import com.spring.delivery.mapper.SongMapper;
import com.spring.delivery.model.Playlist;
import com.spring.delivery.model.Song;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.FavoriteRepository;
import com.spring.delivery.repository.PlaylistRepository;
import com.spring.delivery.repository.SongRepository;
import com.spring.delivery.service.cloudinary.CloudinaryService;
import com.spring.delivery.util.PageableUtil;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PlaylistServiceImpl implements PlaylistService {
    PlaylistRepository playlistRepository;
    PlayListMapper playListMapper;
    SongMapper songMapper;
    SongRepository songRepository;
    PageableUtil pageableUtil;
    SecurityUtil securityUtil;
    CloudinaryService cloudinaryService;
    private final FavoriteRepository favoriteRepository;

    @Override
    public ResponsePlaylistCreated createPlaylist(RequestPlaylistCreated request) {
        var user = securityUtil.getCurrentUserDTO();
        if (user.isEmpty())
            throw new AppException(AppErrorCode.UNAUTHORIZED);
        if (playlistRepository.existsByName(request.name())) {
            log.error("Playlist with name {} already exists", request.name());
            throw new AppException(AppErrorCode.EXIST);
        }
        Playlist playlist = playListMapper.toPlaylist(request);
        playlist.setUser(new User(user.get().id()));
        playlist = playlistRepository.save(playlist);
        return toResponsePlaylistCreated(playlist);
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.findById(id).orElseThrow(() -> {
            log.error("Playlist with id {} not found", id);
            return new AppException(AppErrorCode.NOT_FOUND);
        });
        playlistRepository.deleteById(id);
    }

    @Override
    public void addSongToPlaylist(Long id, Long songId) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> {
            log.error("Playlist with id {} not found", id);
            return new AppException(AppErrorCode.NOT_FOUND);
        });

        Song song = songRepository.findById(songId).orElseThrow(() -> {
            log.error("Song with id {} not found", songId);
            return new AppException(AppErrorCode.NOT_FOUND);
        });

        if (playlist.getSongs().contains(song)) {
            log.error("Song with id {} already exists in playlist with id {}", songId, id);
            throw new AppException(AppErrorCode.EXIST);
        }


        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

    @Override
    public void removeSongFromPlaylist(Long id, Long songId) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> {
            log.error("Playlist with id {} not found", id);
            throw new AppException(AppErrorCode.NOT_FOUND);
        });

        Song song = songRepository.findById(songId).orElseThrow(() -> {
            log.error("Song with id {} not found", songId);
            throw new AppException(AppErrorCode.NOT_FOUND);
        });

        if (!playlist.getSongs().contains(song)) {
            log.error("Song with id {} already exists in playlist with id {}", songId, id);
            throw new AppException(AppErrorCode.EXIST);
        }

        playlist.getSongs().remove(song);
        playlistRepository.save(playlist);
    }

    private ResponsePlaylistCreated toResponsePlaylistCreated(Playlist entity) {
        ResponsePlaylistCreated response = playListMapper.toResponsePlaylistCreated(entity);
        if (entity.getCover() != null)
            response.setCover(cloudinaryService.generateImage(entity.getCover().getPublicId()));

        return response;
    }

    @Override
    public ApiPaging<ResponsePlaylistCard> getPlayList(Pageable pageable) {
        var user = securityUtil.getCurrentUserDTO();
        if (user.isEmpty())
            throw new AppException(AppErrorCode.UNAUTHORIZED);

        Page<Playlist> page = playlistRepository.findByUser_Id(user.get().id(), pageable);
        var response = pageableUtil.handlePaging(page, playListMapper::toPlaylistCardResponse);

        response.getContent().forEach(pl -> setPlaylistCoverUrl(pl, page.getContent()));

        return response;
    }

    @Override
    public ApiPaging<ResponsePlaylistCard> getPlayListNonAuth(Pageable pageable) {
        Page<Playlist> page = playlistRepository.findAll(pageable);
        var response = pageableUtil.handlePaging(page, playListMapper::toPlaylistCardResponse);

        response.getContent().forEach(pl -> setPlaylistCoverUrl(pl, page.getContent()));

        return response;
    }

    private void setPlaylistCoverUrl(ResponsePlaylistCard responsePlaylistCard, List<Playlist> playlists) {
        if (responsePlaylistCard.getCoverUrl() != null && !responsePlaylistCard.getCoverUrl().isEmpty()) return;
        var optionalPlaylist = playlists.stream().filter(p -> Objects.equals(p.getId(), responsePlaylistCard.getId())).findFirst();
        if (optionalPlaylist.isEmpty()) return;
        var song = optionalPlaylist.get().getSongs().stream().findFirst();
        if (song.isEmpty()) return;
        String url = cloudinaryService.generateImage(song.get().getCover().getPublicId());
        responsePlaylistCard.setCoverUrl(url);
    }

    @Override
    public ResponsePlaylistDetail getPlayListDetail(Long id, Pageable pageable) {
        var user = securityUtil.getCurrentUserDTO();
        if (user.isEmpty())
            throw new AppException(AppErrorCode.UNAUTHORIZED);

        var playlist = playlistRepository.findByIdAndUser_Id(id, user.get().id());
        var response = playListMapper.toPlaylistDetailResponse(playlist.orElseThrow(() -> new AppException(AppErrorCode.NOT_FOUND)));
        setPlaylistCoverUrl(response, List.of(playlist.get()));
        var songs = playlist.get().getSongs().stream().skip((long) pageable.getPageNumber() * pageable.getPageSize()).limit(pageable.getPageSize()).map(this::toSongResponseCard).toList();
        var totalPage = playlist.get().getSongs().size() / pageable.getPageSize();
        response.setSongs(ApiPaging.<ResponseSongCard>builder()
                .content(songs)
                .totalPages(totalPage + 1)
                .isLast(pageable.getPageNumber() == totalPage)
                .isFirst(pageable.getPageNumber() == 0)
                .currentPage(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalItems(songs.size())
                .build());
        response.setAllSongId(playlist.get().getSongs().stream().mapToLong(Song::getId).boxed().toList());
        return response;
    }

    @Override
    public ResponsePlaylistDetail getFavoriteSongs(Pageable pageable) {
        var user = securityUtil.getCurrentUserDTO();
        if (user.isEmpty())
            throw new AppException(AppErrorCode.UNAUTHORIZED);

        var id = user.get().id();
        var favoriteList = favoriteRepository.findByUser_Id(id);
        var playlistDetail = new ResponsePlaylistDetail() {{
            setName("My favorites");
            setTotalSong(favoriteList.size());
            setDescription("All song i love");
            setAllSongId(favoriteList.stream().mapToLong(it -> it.getSong().getId()).boxed().toList());
        }};
        var favoritePaging = favoriteList.stream().skip((long) pageable.getPageSize() * pageable.getPageNumber()).limit(pageable.getPageSize()).toList();
        var totalPage = (favoriteList.size() / pageable.getPageSize());
        playlistDetail.setSongs(ApiPaging.<ResponseSongCard>builder()
                .content(favoritePaging.stream().map(it -> toSongResponseCard(it.getSong())).collect(Collectors.toList()))
                .totalPages(totalPage + 1)
                .isLast(pageable.getPageNumber() == totalPage)
                .isFirst(pageable.getPageNumber() == 0)
                .currentPage(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalItems(favoritePaging.size())
                .build());
        if (playlistDetail.getCoverUrl() != null && !playlistDetail.getCoverUrl().isEmpty()) return playlistDetail;
        var favorite = favoriteList.stream().findFirst();
        if (favorite.isEmpty()) return playlistDetail;
        String url = cloudinaryService.generateImage(favorite.get().getSong().getCover().getPublicId());
        playlistDetail.setCoverUrl(url);
        return playlistDetail;
    }

    @Override
    public ApiPaging<ResponsePlaylistCard> getPlaylistNotHasSong(String name, long id, Pageable pageable) {
        var user = securityUtil.getCurrentUserDTO();
        if (user.isEmpty())
            throw new AppException(AppErrorCode.UNAUTHORIZED);

        Page<Playlist> page = playlistRepository.findByNameLikeAndUser_IdAndSongsNotContains(
                "%" + name + "%",
                user.get().id()
                , new Song() {{
                    setId(id);
                }}, pageable);
        var response = pageableUtil.handlePaging(page, playListMapper::toPlaylistCardResponse);

        response.getContent().forEach(pl -> setPlaylistCoverUrl(pl, page.getContent()));

        return response;
    }

    private ResponseSongCard toSongResponseCard(Song song) {
        ResponseSongCard responseSong = songMapper.toSongCardResponse(song);
        String url = cloudinaryService.generateImage(song.getCover().getPublicId());
        responseSong.setCover(url);
        return responseSong;
    }


}
