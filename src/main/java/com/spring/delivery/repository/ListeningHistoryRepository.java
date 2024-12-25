package com.spring.delivery.repository;

import com.spring.delivery.model.ListeningHistory;
import com.spring.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningHistoryRepository extends JpaRepository<ListeningHistory, Long> {
    @Query(value = """
            SELECT h.*
            FROM listening_histories h
                     JOIN (
                SELECT song_id, MAX(created_at) AS latest_created_at
                FROM listening_histories
                GROUP BY song_id
            ) latest
                          ON h.song_id = latest.song_id AND h.created_at = latest.latest_created_at
            ORDER BY h.created_at DESC;
            """, nativeQuery = true)
    List<ListeningHistory> findByUserId(Long userId);


}
