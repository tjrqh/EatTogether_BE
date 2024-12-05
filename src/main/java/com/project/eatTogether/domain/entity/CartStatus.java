package com.project.eatTogether.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CartStatus {
    ACTIVE, ORDERED, CANCELLED


}
