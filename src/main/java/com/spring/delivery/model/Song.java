package com.spring.delivery.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "songs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Song extends BaseModel {
    String title;
    String album;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    Genre genre;
    Long duration;
    @OneToOne
    Resource source;
    @OneToOne
    Resource cover;
    @ManyToOne
    Artist artist;

    @ManyToMany(mappedBy = "songs")
    Set<Playlist> playlists;

    @ManyToMany(mappedBy = "songs")
    Set<User> users;

    @OneToMany(mappedBy = "song")
    Set<ListeningHistory> listeningHistories;
}