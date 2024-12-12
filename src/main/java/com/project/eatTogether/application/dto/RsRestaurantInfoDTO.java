package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsRestaurantInfoDTO {
    private Long rsDetailsId;       // 상세 정보 ID
    private String rsDescription;   // 식당 설명
    private String corkage;         // 콜키지 정보
    private String parkInfo;        // 주차 정보
    private Long rsId;              // 식당 ID (외래 키)
}
