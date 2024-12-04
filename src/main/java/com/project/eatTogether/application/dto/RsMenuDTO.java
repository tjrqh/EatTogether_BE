package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsMenuDTO {
    private Long menuId; // 메뉴 ID
    private Long rsId; // 식당 ID
    private String itemName; // 아이템 이름
    private String itemDesc; // 아이템 설명
    private Double itemPrice; // 아이템 가격
    private Boolean itemState; // 아이템 상태
    private Boolean itemAppear; // 아이템 노출 여부
    private String itemPhotoOrigin; // 아이템 사진 원본
    private String itemPhotoPath; // 아이템 사진 경로
    private String itemPhotoName; // 아이템 사진 이름
    private String itemCreatedAt; // 생성일
    private String itemUpdatedAt; // 수정일
    private String itemDeletedAt; // 삭제일
}
