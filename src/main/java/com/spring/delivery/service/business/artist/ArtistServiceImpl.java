package com.spring.delivery.service.business.artist;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.ResponseArtistCard;
import com.spring.delivery.mapper.ArtistMapper;
import com.spring.delivery.model.Artist;
import com.spring.delivery.repository.ArtistRepository;
import com.spring.delivery.service.cloudinary.CloudinaryService;
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
public class ArtistServiceImpl implements ArtistService {
    ArtistRepository artistRepository;
    ArtistMapper artistMapper;
    PageableUtil pageableUtil;
    CloudinaryService cloudinaryService;

    @Override
    public ApiPaging<ResponseArtistCard> getArtistCard(Pageable pageable) {
        Page<Artist> page = artistRepository.findTopArtists(pageable);
        pageableUtil.checkNoEmpty(page);
        return pageableUtil.handlePaging(page, this::toArtistCardResponse);
    }

    private ResponseArtistCard toArtistCardResponse(Artist artist) {
        if (artist == null) {
            return null;
        }

        ResponseArtistCard responseArtistCard = artistMapper.toArtistCardResponse(artist);
        if (artist.getAvatar() == null) {
            String url = cloudinaryService.generateImage(artist.getAvatar().getPublicId());
            responseArtistCard.setAvatar(url);
        }
        return responseArtistCard;
    }
}
