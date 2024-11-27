package com.spring.delivery.repository;


import com.spring.delivery.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("""
            SELECT a 
            FROM Artist a 
            JOIN a.songs s 
            GROUP BY a 
            ORDER BY SUM(s.views) DESC
            """)
    Page<Artist> findTopArtists(Pageable pageable);

    Optional<Artist> findByName(String name);


}