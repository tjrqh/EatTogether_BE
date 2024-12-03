package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueingRepository extends JpaRepository<Queue, Long> {
}
