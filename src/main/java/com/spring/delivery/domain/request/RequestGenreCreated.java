/**
 * Class: RequestGenreCreated
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.domain.request;

import java.util.List;

public record RequestGenreCreated(
        String name,
        String description,
        Long coverId,
        List<Long> songId
) {
}
