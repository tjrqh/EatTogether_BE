package com.project.eatTogether.application.dto.userDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenValidationResponse {
    private final boolean valid;
}
