/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:59 AM - 04/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.search;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.RequestSearch;
import com.spring.delivery.domain.response.ResponseSearch;
import org.springframework.data.domain.Pageable;

public interface ISearchService {
    ApiPaging<ResponseSearch> search(RequestSearch request, Pageable pageable);
}
