package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsAmenitiesDTO {
    private Long rsAmenityId; // 편의시설 코드 ID
    private String rsAmenityName; // 편의시설 이름
    private String rsPark; // 주차
    private String rsTime; // 영업시간
}
