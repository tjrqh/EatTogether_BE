package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    static void save(java.util.Queue queue) {
    }
}
