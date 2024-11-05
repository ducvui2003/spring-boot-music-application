package com.spring.delivery.service.business.song;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseSong;
import com.spring.delivery.mapper.SongMapper;
import com.spring.delivery.model.Song;
import com.spring.delivery.repository.SongRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongServiceImpl implements SongService {
    SongRepository songRepository;
    SongMapper songMapper = SongMapper.INSTANCE;

    @Override
    public ApiPaging<ResponseSong> getSongs(Pageable pageable) {
        Page<Song> page = songRepository.findAll(pageable);
        List<ResponseSong> data = page.getContent().stream()
                .map(songMapper::toSongResponse)
                .toList();
        return ApiPaging.<ResponseSong>builder()
                .totalItems((int) page.getTotalElements())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber())
                .size(page.getSize())
                .isLast(page.isLast())
                .isFirst(page.isFirst())
                .content(data)
                .build();
    }
}
