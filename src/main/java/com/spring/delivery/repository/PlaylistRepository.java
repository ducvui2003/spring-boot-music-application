package com.spring.delivery.repository;

import com.spring.delivery.model.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    boolean existsByName(String name);

    Page<Playlist> findByUser_Id(Long id, Pageable pageable);
}
