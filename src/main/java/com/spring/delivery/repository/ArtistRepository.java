package com.spring.delivery.repository;


import com.spring.delivery.model.Artist;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    // Custom query to get the top artists based on the total views of their songs
    @Query("""
            SELECT a FROM Artist a join Song s GROUP BY a.id ORDER BY SUM(s.views) DESC 
            """)
    List<Artist> findTopArtists(PageRequest pageable);
}