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
@Table(name = "playlists")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Playlist extends BaseModel {
    String name;
    String description;
    @OneToOne
    Resource cover;
    boolean isPublic;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToMany
    @JoinTable(name = "playlists_songs", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
    Set<Song> songs;
}
