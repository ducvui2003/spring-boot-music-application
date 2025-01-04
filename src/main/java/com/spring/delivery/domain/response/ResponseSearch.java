/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:48 AM - 04/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.response;

import com.spring.delivery.util.enums.SearchTypeEntry;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSearch implements Comparable<ResponseSearch> {
    Object data;
    SearchTypeEntry type;

    @Override
    public int compareTo(ResponseSearch responseSearch) {
        return responseSearch.uuTien() - this.uuTien();
    }

    private int uuTien() {
        return switch (type) {
            case All -> 0;
            case Song -> 1;
            case Album -> 2;
            case Artist -> 3;
        };
    }
}
