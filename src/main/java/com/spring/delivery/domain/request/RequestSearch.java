/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:51 AM - 04/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.request;

import com.spring.delivery.util.enums.SearchTypeEntry;

public record RequestSearch(
        String name,
        SearchTypeEntry type
) {
    public RequestSearch {
        type = SearchTypeEntry.All;
    }
}
