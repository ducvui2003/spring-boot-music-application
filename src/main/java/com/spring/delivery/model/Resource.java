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

@Table(name = "images")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Resource {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    String url;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    Tag tag;
    String publicId;
}
