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
    private String rsAmenityId; // 편의시설 코드 id
    private String rsAmenityName; // 편의시설 이름
}
