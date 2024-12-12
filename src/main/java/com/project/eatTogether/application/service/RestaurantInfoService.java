package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsRestaurantInfoDTO;
import com.project.eatTogether.infrastructure.RsRestaurantInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantInfoService {

    private final RsRestaurantInfoRepository restaurantInfoRepository;

    public List<RsRestaurantInfoDTO> getDetailsByRestaurantId(Long rsId) {
        return restaurantInfoRepository.findByRsRestaurantRsId(rsId)
                .stream()
                .map(detail -> RsRestaurantInfoDTO.builder()
                        .rsDetailsId(detail.getRsDetailsId())
                        .rsDescription(detail.getRsDescription())
                        .corkage(detail.getCorkage())
                        .parkInfo(detail.getParkInfo())
                        .rsId(detail.getRsRestaurant().getRsId())
                        .build())
                .collect(Collectors.toList());
    }
}
