package com.project.eatTogether.application.service;


import com.project.eatTogether.application.dto.map.MapResponseDto;
import com.project.eatTogether.domain.entity.RsCoordinates;
import com.project.eatTogether.infrastructure.MapReposotory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapReposotory mapReposotory;

    public List<MapResponseDto> getAllThousandAddr() {
        List<RsCoordinates> result = mapReposotory.findAll();
        return result.stream()
                .map(MapResponseDto::entityToDto)
                .collect(Collectors.toList());
    }
}
