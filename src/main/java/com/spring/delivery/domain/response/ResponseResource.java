/**
 * Class: ResourceResponse
 *
 * @author ducvui2003
 * @created 13/10/24
 */
package com.spring.delivery.domain.response;

import com.spring.delivery.util.enums.Tag;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseResource {
    Long id;
    String url;
    Tag tag;
}
