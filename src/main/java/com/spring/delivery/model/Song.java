package com.spring.delivery.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigInteger;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "songs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Song extends BaseModel implements Comparable<Song> {
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

    @OneToMany(mappedBy = "song")
    Set<Favorite> favorites;

    @OneToMany(mappedBy = "song")
    Set<ListeningHistory> listeningHistories;

    @ColumnDefault("false")
    boolean isPremium;

    @ManyToOne
    @JoinColumn(name = "album_id")
    Album album;

    @Override
    public int compareTo(Song song) {
        return Long.compare(this.getId(), song.getId());
    }
}
