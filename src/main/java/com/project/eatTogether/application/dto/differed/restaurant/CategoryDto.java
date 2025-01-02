package com.project.eatTogether.application.dto.differed.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoryDto {
    private Long id;      // enum의 name()
    private String name;    // enum의 displayName
}