/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:09 AM - 04/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Favorite extends BaseModel implements Comparable<Favorite> {
    @ManyToOne
    @JoinColumn(name = "song_id")
    Song song;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime addAt = LocalDateTime.now();

    @Override
    public int compareTo(Favorite favorite) {
        return Long.compare(this.getId(), favorite.getId());
    }
}
