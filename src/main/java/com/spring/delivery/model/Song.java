package com.spring.delivery.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigInteger;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "songs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Song extends BaseModel {
    String title;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    Genre genre;
    @OneToOne
    Resource source;
    @OneToOne
    Resource cover;
    @ManyToOne
    Artist artist;

    BigInteger views;

    @ManyToMany(mappedBy = "songs")
    Set<Playlist> playlists;

    @ManyToMany(mappedBy = "songs")
    Set<User> users;

    @OneToMany(mappedBy = "song")
    Set<ListeningHistory> listeningHistories;

    @ColumnDefault("false")
    boolean isPremium = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "album_id")
    Album album;

    @ColumnDefault("false")
    boolean deleted = Boolean.FALSE;
}
