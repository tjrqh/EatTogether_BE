package com.project.eatTogether.application.service.differed;

import com.project.eatTogether.application.dto.differed.map.MapResponseDto;
import com.project.eatTogether.domain.entity.differed.Coordinates;
import com.project.eatTogether.infrastructure.differed.MapReposotory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapReposotory mapReposotory;

    public List<MapResponseDto> getAllThousandAddr() {
        List<Coordinates> result = mapReposotory.findAll();
        return result.stream()
                .map(MapResponseDto::entityToDto)
                .collect(Collectors.toList());
    }
}
