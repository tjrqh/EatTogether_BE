package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {

    private Long cartId;    // 장바구니id
    private int basketAmount;   // 메뉴수량
    private LocalDateTime basketCreatedAt;  // 등록일시
    private LocalDateTime basketUpdatedAt;  // 수정일시
    private LocalDateTime basketDeletedAt;  // 삭제일시
    private Long rsId;  // 식당 ID
    private Long userId;    // 유저 id
    private Long queueId;

}

