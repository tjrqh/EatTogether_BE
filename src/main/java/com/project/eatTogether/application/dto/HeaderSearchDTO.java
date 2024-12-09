package com.project.eatTogether.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class HeaderSearchDTO {
    private String rsName; // 식당 이름
    private String rsLocationName; // 위치 이름
    private String rsCuisineCategoryName; // 식당종류 이름

    public HeaderSearchDTO(Object[] obj) {
        this.rsLocationName = obj[0].toString();
        this.rsName = obj[1].toString();
        this.rsCuisineCategoryName = obj[2].toString();
    }
}
