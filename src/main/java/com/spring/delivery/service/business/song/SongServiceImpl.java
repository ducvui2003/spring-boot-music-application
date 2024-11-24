package com.spring.delivery.service.business.song;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.domain.response.ResponseSongCard;
import com.spring.delivery.mapper.SongMapper;
import com.spring.delivery.model.Song;
import com.spring.delivery.repository.SongRepository;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongServiceImpl implements SongService {
    SongRepository songRepository;
    SongMapper songMapper;
    SecurityUtil securityUtil;
    UserRepository userRepository;

    @Override
    public ApiPaging<ResponseSongCard> getSongCard(Pageable pageable) {
        Page<Song> page = songRepository.findAll(pageable);
        List<ResponseSongCard> data = page.getContent().stream().map(songMapper::toSongCardResponse).toList();
        return ApiPaging.<ResponseSongCard>builder().totalItems((int) page.getTotalElements()).totalPages(page.getTotalPages()).currentPage(page.getNumber()).size(page.getSize()).isLast(page.isLast()).isFirst(page.isFirst()).content(data).build();
    }

    @Override
    public ResponseSong getSongDetail(Long id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isEmpty()) {
            throw new AppException("Song not found");
        }
        return songMapper.toSongResponse(song.get());
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
}
