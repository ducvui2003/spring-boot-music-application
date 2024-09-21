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
@Table(name = "genres")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre extends BaseModel {
    String name;
    String description;
    String cover;

    @OneToMany(mappedBy = "genre")
    Set<Song> songs;
}
