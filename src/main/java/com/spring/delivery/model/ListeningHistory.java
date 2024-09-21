package com.spring.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "listening_histories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListeningHistory extends BaseModel {
    Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Song song;
}
