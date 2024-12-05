package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartItemDTO {

    private Long cartItemId;    // 장바구니아이템id
    private int cartItemAmount; // 장바구니아이템수량
    private Long cartId;    // 장바구니id
    private Long menuId; // 메뉴 ID

}

