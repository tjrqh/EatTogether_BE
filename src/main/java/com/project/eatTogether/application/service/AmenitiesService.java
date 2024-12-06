/*
package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsAmenitiesDTO;
import com.project.eatTogether.domain.RsAmenities;
import com.project.eatTogether.infrastructure.RsRestaurantAmenitiesMappingRepository;
import com.project.eatTogether.infrastructure.RsAmenitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenitiesService {

    private final RsRestaurantAmenitiesMappingRepository amenitiesMappingRepository;
    private final RsAmenitiesRepository amenitiesRepository;

    public List<RsAmenitiesDTO> getAmenitiesByRestaurantId(Long restaurantId) {
        List<Long> amenitiesIds = amenitiesMappingRepository.findByRsRestaurantRsId(restaurantId)
                .stream()
                .map(mapping -> mapping.getRsAmenities().getRsAmenityId())
                .collect(Collectors.toList());

        return amenitiesIds.stream()
                .map(id -> {
                    RsAmenities amenity = amenitiesRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Amenity not found"));
                    return RsAmenitiesDTO.builder()
                            .rsAmenityId(amenity.getRsAmenityId())
                            .rsAmenityName(amenity.getRsAmenityName())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
*/
