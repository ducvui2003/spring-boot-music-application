package com.spring.delivery.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.spring.delivery.util.enums.converter.AuthTypeConverter;
import com.spring.delivery.util.enums.AuthType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;

    @JsonIgnore
    String password;
    String phoneNumber;
    String fullName;

    boolean sex;
    Date birthDay;

    boolean verified;

    @Column(nullable = false)
    @Convert(converter = AuthTypeConverter.class)
    AuthType authType = AuthType.USERNAME_PASSWORD;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @OneToMany(mappedBy = "user")
    Set<Playlist> playlists;

    @ManyToMany
    @JoinTable(name = "favorites", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
    Set<Song> songs;

    @OneToMany(mappedBy = "user")
    List<ListeningHistory> listeningHistories;
}
