package com.spring.delivery.repository;

import com.spring.delivery.model.Playlist;
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
import java.util.Optional;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findAll(Pageable pageable);

    @Query("SELECT s FROM Song s ORDER BY s.views DESC")
    Page<Song> findSongPopular(Pageable pageable);

    Set<Song> findAllByIdIn(List<Long> ids);

    Optional<Song> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Song s SET s.views = s.views + 1 WHERE s.id = :songId")
    int incrementViewCount(@Param("songId") Long songId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO favorites (user_id, song_id) " +
            "SELECT :userId, :songId FROM songs s WHERE s.id = :songId",
            nativeQuery = true)
    int addSongToFavoriteIfExists(@Param("userId") Long userId, @Param("songId") Long songId);


    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :userId AND :songId MEMBER OF u.songs")
    void removeSongFromFavoriteIfExists(@Param("userId") long userId, @Param("songId") Long songId);

    List<Song> findAll();

    List<Song> findByPlaylistsContains(Playlist playlist, Pageable pageable);
}
