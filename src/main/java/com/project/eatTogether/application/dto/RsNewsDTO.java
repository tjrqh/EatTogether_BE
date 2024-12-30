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
public class RsNewsDTO {
    private Long rsNewsId; // 소식 ID
    private Long rsId; // 식당 ID
    private String rsNewsContent; // 소식 내용
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime modifiedAt; // 수정일
    private LocalDateTime deletedAt; // 삭제일
}
