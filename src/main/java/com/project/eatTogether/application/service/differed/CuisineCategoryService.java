package com.project.eatTogether.application.service.differed;

import com.project.eatTogether.application.dto.differed.restaurant.CategoryDto;
import com.project.eatTogether.domain.enums.CuisineType;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional(readOnly = true)
public class CuisineCategoryService {

    public List<CategoryDto> getAllCategories() {
        return Arrays.stream(CuisineType.values())
                .map(type -> CategoryDto.builder()
                        .id(Long.valueOf(type.ordinal()))          // enum의 이름 (KOREAN, JAPANESE 등)
                        .name(type.getDisplayName()) // 표시 이름 (한식, 일식 등)
                        .build())
                .collect(Collectors.toList());
    }

    // enum 값으로 CuisineType 조회
    public CuisineType getCategoryByName(String name) {
        try {
            return CuisineType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("존재하지 않는 카테고리입니다: " + name);
        }
    }
}