/**
 * Class: Image
 *
 * @author ducvui2003
 * @created 12/10/24
 */
package com.spring.delivery.model;

import com.spring.delivery.util.enums.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "resources")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Resource extends BaseModel {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    Tag tag;
    String publicId;
    String name;
}
