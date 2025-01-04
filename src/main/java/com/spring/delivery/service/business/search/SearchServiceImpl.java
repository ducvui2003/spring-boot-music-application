/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 12:00 PM - 04/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.search;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestSearch;
import com.spring.delivery.domain.response.ResponseSearch;
import com.spring.delivery.service.business.album.AlbumService;
import com.spring.delivery.service.business.artist.ArtistService;
import com.spring.delivery.service.business.song.SongService;
import com.spring.delivery.util.enums.SearchTypeEntry;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Service
public class SearchServiceImpl implements ISearchService {
    ArtistService artistService;
    SongService songService;
    AlbumService albumService;

    @Override
    public ApiPaging<ResponseSearch> search(RequestSearch request, Pageable pageable) {
        var data = getEntry(request);
        Collections.sort(data);
        var content = data.stream().skip((long) pageable.getPageNumber() * pageable.getPageSize()).limit(pageable.getPageSize()).toList();
        var totalPage = data.size() / pageable.getPageSize();
        return ApiPaging.<ResponseSearch>builder()
                .totalPages(totalPage + 1)
                .isLast(pageable.getPageNumber() == totalPage)
                .isFirst(pageable.getPageNumber() == 0)
                .currentPage(pageable.getPageNumber())
                .totalItems(content.size())
                .content(content)
                .size(pageable.getPageSize())
                .build();
    }

    private List<ResponseSearch> getEntry(RequestSearch request) {
        return switch (request.type()) {
            case All -> {
                var result = new ArrayList<ResponseSearch>();
                result.addAll(songService.search(request.name()).stream().map(it -> ResponseSearch.builder()
                                .data(it)
                                .type(SearchTypeEntry.Song)
                                .build())
                        .toList());
                result.addAll(albumService.search(request.name()).stream().map(it -> ResponseSearch.builder()
                                .data(it)
                                .type(SearchTypeEntry.Album)
                                .build())
                        .toList());
                result.addAll(artistService.findByName(request.name()).stream().map(it -> ResponseSearch.builder()
                                .data(it)
                                .type(SearchTypeEntry.Artist)
                                .build())
                        .toList());
                yield result;
            }
            case Song -> songService.search(request.name()).stream()
                    .map(it -> ResponseSearch.builder()
                            .data(it)
                            .type(SearchTypeEntry.Song)
                            .build())
                    .collect(Collectors.toCollection(ArrayList::new));
            case Album -> albumService.search(request.name()).stream()
                    .map(it -> ResponseSearch.builder()
                            .data(it)
                            .type(SearchTypeEntry.Album)
                            .build())
                    .collect(Collectors.toCollection(ArrayList::new));
            case Artist -> artistService.findByName(request.name()).stream()
                    .map(it -> ResponseSearch.builder()
                            .data(it)
                            .type(SearchTypeEntry.Artist)
                            .build())
                    .collect(Collectors.toCollection(ArrayList::new));
        };
    }
}
