package com.spring.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "albums")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Album extends BaseModel {
    String name;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    Artist artist;
    String cover;
    Instant releaseDate;
}
