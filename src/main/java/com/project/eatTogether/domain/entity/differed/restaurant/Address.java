package com.project.eatTogether.domain.entity.differed.restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Address {
    @Column(nullable = false)
    private String street;         // 도로명 주소

    @Column(nullable = false)
    private String detail;         // 상세 주소

    @Column(nullable = false)
    private String postcode;        // 우편번호

    private Double latitude;       // 위도
    private Double longitude;      // 경도

    /** 전체 주소*/
    public String getFullAddress() {
        return String.format("[%s] %s %s", postcode, street, detail);
    }
}
