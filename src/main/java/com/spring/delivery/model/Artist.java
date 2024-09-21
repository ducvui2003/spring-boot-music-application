package com.spring.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "artists")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Artist extends BaseModel {
    String name;
    String bio;
    String avatar;

    @OneToMany(mappedBy = "artist")
    Set<Song> songs;

    @OneToMany(mappedBy = "artist")
    Set<Album> albums;
}
