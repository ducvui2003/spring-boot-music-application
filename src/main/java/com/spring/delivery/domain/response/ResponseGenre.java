/**
 * Class: ResponseGerne
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.domain.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseGenre {
    String id;
    String name;
    String description;
    String cover;
}
