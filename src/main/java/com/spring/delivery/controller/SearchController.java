/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:47 AM - 04/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestSearch;
import com.spring.delivery.domain.response.ResponseSearch;
import com.spring.delivery.service.business.search.ISearchService;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/search")
public class SearchController {
    ISearchService searchService;

    @GetMapping()
    @ApiMessage("Get success")
    public ResponseEntity<ApiPaging<ResponseSearch>> search(RequestSearch search, @PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(searchService.search(search, pageable));
    }
}
