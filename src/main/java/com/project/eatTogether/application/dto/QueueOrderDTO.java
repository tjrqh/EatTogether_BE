package com.project.eatTogether.application.dto;

import com.project.eatTogether.domain.Payment;
import com.project.eatTogether.domain.QueueOrderItem;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueOrderDTO {

    private Long queueOrderId;              // 줄서기주문Id
    private long totalAmount;                // 총수량
    private LocalDateTime orderDateTime;    // 주문일시
    private String orderStatus;             // 주문상태
    private Long rsId;                      // 식당ID
    private Long userId;                    // 유저ID
    private Long paymentId;                // 결제id

}
