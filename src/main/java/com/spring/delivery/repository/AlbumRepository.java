package com.spring.delivery.repository;

import com.spring.delivery.model.Album;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a join Song s GROUP BY a.id ORDER BY SUM(s.views) DESC")
    List<Album> findTopAlbums(PageRequest pageable);
}