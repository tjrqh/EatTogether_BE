package com.project.eatTogether.application.dto;

import com.project.eatTogether.domain.QueueOrder;
import com.project.eatTogether.domain.RsMenus;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueOrderItemDTO {

    private Long queueOrderItemId;  // 줄서기 주문 id
    private int queueOrderItemAmount;   // 총수량
    private int queueOrderItemPrice;    // 총가격
    private Long menuId;    // 메뉴 ID
    private Long queueOrderId;  // 줄서기주문Id

}
