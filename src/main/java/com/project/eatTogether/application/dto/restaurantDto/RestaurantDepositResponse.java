package com.project.eatTogether.application.dto.restaurantDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDepositResponse {
    private boolean rsDepositRequired;    // 예약금 필요 여부
    private int rsDepositAmount;          // 1인당 예약금 금액
}
