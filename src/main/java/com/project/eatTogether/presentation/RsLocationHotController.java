package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsLocationHotDTO;
import com.project.eatTogether.application.service.RsLocationHotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RsLocationHotController {

    private final RsLocationHotService locationHotService;

    @GetMapping("/location-category")
    public List<RsLocationHotDTO> getLocationHotCategory(@RequestParam String rsLocationCategoriesName,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return locationHotService.getLocationHotByName(rsLocationCategoriesName, page, size);
    }
}
