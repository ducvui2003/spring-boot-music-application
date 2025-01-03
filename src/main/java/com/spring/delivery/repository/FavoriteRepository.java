/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:17 AM - 04/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.repository;

import com.spring.delivery.model.Favorite;
import com.spring.delivery.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByIdAndUser_Id(long id, long userId);

    Page<Favorite> findByUser_Id(long userId, Pageable pageable);
    
    List<Favorite> findByUser_Id(long userId);

    int countByUser_Id(long userId);

    List<Favorite> user(User user);

    @Transactional
    void removeBySong_IdAndUser_Id(long songId, Long useId);

    boolean existsBySong_Id(Long id);
}
