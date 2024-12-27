package com.spring.delivery.service.business.album;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseAlbumCard;
import com.spring.delivery.domain.response.ResponseAlbumDetail;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.mapper.AlbumMapper;
import com.spring.delivery.mapper.SongMapper;
import com.spring.delivery.model.Album;
import com.spring.delivery.model.Song;
import com.spring.delivery.repository.AlbumRepository;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlbumServiceImpl implements AlbumService {
    AlbumRepository albumRepository;
    PageableUtil pageableUtil;
    AlbumMapper albumMapper;
    CloudinaryService cloudinaryService;
    SecurityUtil securityUtil;
    SongMapper songMapper;

    @Override
    public ApiPaging<ResponseAlbumCard> getAlbumCard(Pageable pageable) {
        Page<Album> page = albumRepository.findTopAlbums(pageable);
        ApiPaging<ResponseAlbumCard> apiPaging = pageableUtil.handlePaging(page, entity -> {
            ResponseAlbumCard response = albumMapper.toResponseAlbumCard(entity);
            response.setCoverUrl(getCoverAlbum(entity));
            return response;
        });
        return apiPaging;
    }

    @Override
    public ResponseAlbumDetail getDetail(Long id, Pageable pageable) {
        var user = securityUtil.getCurrentUserDTO();
        if (user.isEmpty())
            throw new AppException(AppErrorCode.UNAUTHORIZED);

        var album = albumRepository.findById(id);
        if (album.isEmpty()) {
            throw new AppException(AppErrorCode.NOT_FOUND);
        }

        var response = albumMapper.toAlbumDetailResponse(album.orElseThrow(() -> new AppException(AppErrorCode.NOT_FOUND)));
        response.setCoverUrl(getCoverAlbum(album.get()));
        var songs = album.get().getSongs().stream().skip((long) pageable.getPageNumber() * pageable.getPageSize()).limit(pageable.getPageSize()).map(this::toSongResponseCard).toList();
        var totalPage = album.get().getSongs().size() / pageable.getPageSize();
        response.setSongs(ApiPaging.<ResponseSongCard>builder()
                .content(songs)
                .totalPages(totalPage + 1)
                .isLast(pageable.getPageNumber() == totalPage)
                .isFirst(pageable.getPageNumber() == 0)
                .currentPage(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalItems(songs.size())
                .build());
        return response;
    }

    private String getCoverAlbum(Album album) {
        if (album.getCover() != null) return cloudinaryService.generateImage(album.getCover().getPublicId());
        if (album.getSongs().isEmpty()) return null;
        return cloudinaryService.generateImage(album.getSongs().getFirst().getCover().getPublicId());
    }

    private ResponseSongCard toSongResponseCard(Song song) {
        ResponseSongCard responseSong = songMapper.toSongCardResponse(song);
        String url = cloudinaryService.generateImage(song.getCover().getPublicId());
        responseSong.setCover(url);
        return responseSong;
    }
}
