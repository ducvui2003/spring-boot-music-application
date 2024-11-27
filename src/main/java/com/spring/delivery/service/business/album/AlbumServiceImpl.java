package com.spring.delivery.service.business.album;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseAlbumCard;
import com.spring.delivery.mapper.AlbumMapper;
import com.spring.delivery.model.Album;
import com.spring.delivery.repository.AlbumRepository;
import com.spring.delivery.util.PageableUtil;
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

    @Override
    public ApiPaging<ResponseAlbumCard> getAlbumCard(Pageable pageable) {
        Page<Album> page = albumRepository.findTopAlbums(pageable);
        ApiPaging<ResponseAlbumCard> apiPaging = pageableUtil.handlePaging(page, entity -> albumMapper.toResponseAlbumCard(entity));
        return apiPaging;
    }
}