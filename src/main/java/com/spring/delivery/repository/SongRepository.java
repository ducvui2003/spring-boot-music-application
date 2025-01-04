package com.spring.delivery.repository;

import com.spring.delivery.model.Song;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findAll(Pageable pageable);

    boolean existsByTitle(String title);

    @Query("SELECT s FROM Song s ORDER BY s.views DESC")
    Page<Song> findSongPopular(Pageable pageable);

    Set<Song> findAllByIdIn(List<Long> ids);

    @Modifying
    @Transactional
    @Query("UPDATE Song s SET s.views = s.views + 1 WHERE s.id = :songId")
    int incrementViewCount(@Param("songId") Long songId);

    List<Song> findAllByTitleLike(String name);

    List<Song> findAllByArtist_NameLike(String name);
}
