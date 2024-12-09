package com.project.eatTogether.application.dto;

import com.project.eatTogether.domain.Queue;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueDTO {

    private Long queueId;                   // 줄서기ID
    private Integer queueNumber;                // 줄서기인원수
    private LocalDate queueDate;            // 줄서기날짜
    private LocalTime queueTime;            // 줄서기시간
    private String queueState;              // 줄서기상태
    private LocalDateTime queueCreatedAt;   // 등록일
    private LocalDateTime queueUpdatedAt;   // 수정일
    private LocalDateTime queueDeletedAt;   // 삭제일
    private Long rsId;                      // 식당ID
    private Long userId;                    // 유저ID
    private Boolean isPrepaid;


    public Queue toEntity(RsRestaurant rsRestaurant, User user) {
        return Queue.builder()
                .queueNumber(queueNumber)
                .queueDate(queueDate)
                .queueTime(queueTime)
                .queueState(queueState)
                .queueCreatedAt(queueCreatedAt)
                .queueUpdatedAt(queueUpdatedAt)
                .queueDeletedAt(queueDeletedAt)
                .rsRestaurant(rsRestaurant)
                .user(user)
                .build();
    }
}
