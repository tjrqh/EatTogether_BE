package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.QueueDTO;
import com.project.eatTogether.domain.Queue;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.domain.User;
import com.project.eatTogether.domain.repository.QueueRepository;
import com.project.eatTogether.domain.repository.UserRsRestaurantRepository;
import com.project.eatTogether.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final UserRepository userRepository;
    private final UserRsRestaurantRepository userRsRestaurantRepository;

    // 대기열에 들어가는 메서드
    public Queue save(QueueDTO queueDTO) {
        RsRestaurant rsRestaurant = userRsRestaurantRepository.findById(queueDTO.getRsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));
        User user = userRepository.findById(queueDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        return queueRepository.save(queueDTO.toEntity(rsRestaurant, user));
    }

    // 대기열 조회 메서드
    public List<Queue> getWaitingQueues() {
        return queueRepository.findByQueueState("waiting");
    }
    // 대기 중인 큐 개수
    public Long countWaitingQueues() {
        return queueRepository.countByQueueState("waiting");
    }

    // 특정 큐의 상태를 'waiting'에서 'entered'로 변경하는 메서드
    @Transactional
    public int updateQueueStateToEntered(Long queueId) {
        return queueRepository.updateQueueStateToEntered(queueId);
    }

    //특정 큐의 상태를 'waiting'에서 'cancled'로 변경하는 메서드
    @Transactional
    public int updateQueueStateToCancled(Long queueId) {
        return queueRepository.updateQueueStateToCancled(queueId); }

}