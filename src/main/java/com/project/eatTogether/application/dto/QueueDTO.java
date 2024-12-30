package com.project.eatTogether.application.dto;

import com.project.eatTogether.domain.entity.Queue;
import com.project.eatTogether.domain.entity.QueueOrder;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueDTO {

    private Long queueId;                   // 줄서기ID
    private Integer queueNumber;            // 줄서기인원수
    private LocalDate queueDate;            // 줄서기날짜
    private LocalTime queueTime;            // 줄서기시간
    private String queueState;              // 줄서기상태
    private LocalDateTime createdAt;   // 등록일
    private LocalDateTime modifiedAt;   // 수정일
    private LocalDateTime deletedAt;   // 삭제일
    private Long rsId;                      // 식당ID
    private Long userId;                    // 유저ID
    private String rsName;                  // 식당 이름 추가

    @JsonIgnore
    private QueueOrder queueOrder;          // 대기열에 대한 주문 정보 (순환참조 방지)

    // QueueDTO를 Queue 엔티티로 변환하는 메소드
    public Queue toEntity(RsRestaurant rsRestaurant, User user) {
        return Queue.builder()
                .queueNumber(queueNumber)
                .queueDate(queueDate)      // LocalDate.now() 대신 DTO의 값 사용
                .queueTime(queueTime)      // LocalTime.now() 대신 DTO의 값 사용
                .queueState(queueState)
                .rsRestaurant(rsRestaurant)
                .user(user)
                .build();
    }

    // 빌더 메소드에 rsName 필드 추가
    public static QueueDTOBuilder builder() {
        return new QueueDTOBuilder();
    }
}
