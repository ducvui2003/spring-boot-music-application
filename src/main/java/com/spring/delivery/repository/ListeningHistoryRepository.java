package com.spring.delivery.repository;

import com.spring.delivery.model.ListeningHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListeningHistoryRepository extends JpaRepository<ListeningHistory, Integer> {
}
