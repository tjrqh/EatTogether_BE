package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsMenusDTO {
    private Long menuId;                    // 메뉴 ID
    private Long rsId;                      // 식당 ID
    private String menuName;                // 메뉴 이름
    private String menuDesc;                // 메뉴 설명
    private String menuPrice;               // 메뉴 가격
    private String menuState;               // 메뉴 상태
    private boolean isFeatured;             // 대표 메뉴
    private String menuAppear;              // 메뉴 노출 여부
    private String menuPhotoOrigin;         // 메뉴 사진 원본
    private String menuPhotoPath;           // 메뉴 사진 경로
    private String menuPhotoName;           // 메뉴 사진 이름
    private LocalDateTime createdAt;        // 생성일
    private LocalDateTime modifiedAt;       // 수정일
    private LocalDateTime deletedAt;        // 삭제일
    private String rsName;                  // 식당 이름
}