package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    List<Queue> findByQueueState(String queueState);

    @Query("SELECT COUNT(q) FROM Queue q WHERE q.queueState = :state")
    Long countByQueueState(@Param("state") String state);

    @Modifying
    @Transactional
    @Query("UPDATE Queue q SET q.queueState = 'entered' WHERE q.queueId = :queueId AND q.queueState = 'waiting'")
    int updateQueueStateToEntered(@Param("queueId") Long queueId);

    @Modifying
    @Transactional
    @Query("UPDATE Queue q SET q.queueState = 'cancled' WHERE q.queueId = :queueId AND q.queueState = 'waiting'")
    int updateQueueStateToCancled(@Param("queueId") Long queueId);
}

