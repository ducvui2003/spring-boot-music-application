package com.spring.delivery.repository;

import com.spring.delivery.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a join Song s ON a.artist = s.artist GROUP BY a.id ORDER BY SUM(s.views) DESC")
    Page<Album> findTopAlbums(Pageable pageable);

    Optional<Album> findByName(String album);

    List<Album> findAllByNameLike(String name);

    List<Album> findAllByArtist_NameLike(String artistName);
}