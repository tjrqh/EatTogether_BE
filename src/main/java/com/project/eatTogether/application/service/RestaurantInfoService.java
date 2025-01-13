package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsRestaurantInfoDTO;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantDepositResponse;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.infrastructure.RsRestaurantInfoRepository;
import com.project.eatTogether.infrastructure.RsRestaurantRepository;
import com.project.eatTogether.infrastructure.security.exception.RestaurantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantInfoService {

    private final RsRestaurantInfoRepository restaurantInfoRepository;
    private final RsRestaurantRepository rsRestaurantRepository;

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

    //레스토랑 예약금 조회
    public RestaurantDepositResponse getDepositInfo(Long rsId) {
        RsRestaurant restaurant = rsRestaurantRepository.findById(rsId)
                .orElseThrow(() -> new RestaurantNotFoundException("레스토랑을 찾을 수 없습니다."));

        return new RestaurantDepositResponse(
                restaurant.isRsDepositRequired(),
                restaurant.getRsDepositAmount()
        );
    }

}
