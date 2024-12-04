package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsNewsDTO {
    private int rsId; // 식당 id
    private String rsNewsContent; // 식당 소식 내용
    private Date rsNewsPublishedAt; // 작성일
    private Date rsNewsUpdatedAt; // 수정일
    private Date rsNewsDeletedAt; // 삭제일
}
