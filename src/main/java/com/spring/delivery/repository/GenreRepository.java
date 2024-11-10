package com.spring.delivery.repository;

import com.spring.delivery.model.Genre;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Page<Genre> findAll(Pageable pageable);

    boolean existsByName(String name);

    Optional<Genre> findByName(@NotBlank String genre);
}
