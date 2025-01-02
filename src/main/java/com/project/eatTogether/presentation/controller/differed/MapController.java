package com.project.eatTogether.presentation.controller.differed;


import com.project.eatTogether.application.dto.differed.map.MapResponseDto;
import com.project.eatTogether.application.service.differed.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class MapController {

    private final MapService mapService;

    @GetMapping("/all_thousand_addr")
    public ResponseEntity<List<MapResponseDto>> GetAllThousandAddr () {
        return ResponseEntity.ok(mapService.getAllThousandAddr());
    }

}
