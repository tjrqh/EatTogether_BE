package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsMenusDTO;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantMenuReadResponse;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantMenuUpdateRequest;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.RsMenus;
import com.project.eatTogether.infrastructure.RsMenuRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final RsMenuRepository menuRepository;

    public List<RsMenusDTO> getMenusByRestaurantId(Long rsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return menuRepository.findByRsRestaurantRsId(rsId, pageable)
                .stream()
                .map(menu -> RsMenusDTO.builder()
                        .menuId(menu.getRsMenuId())
                        .rsId(menu.getRsRestaurant().getRsId())
                        .menuName(menu.getRsMenuName())
                        .menuDesc(menu.getRsMenuDesc())
                        .menuPrice(menu.getRsMenuPrice())
                        .menuState(menu.getRsMenuState())
                        .menuAppear(menu.getRsMenuAppear())
                        .isFeatured(menu.isFeatured())
                        .menuPhotoOrigin(menu.getRsMenuPhotoOrigin())
                        .menuPhotoPath(menu.getRsMenuPhotoPath())
                        .menuPhotoName(menu.getRsMenuPhotoName())
                        .menuCreatedAt(menu.getRsMenuCreatedAt())
                        .menuUpdatedAt(menu.getRsMenuUpdatedAt())
                        .menuDeletedAt(menu.getRsMenuDeletedAt())
                        .rsName(menu.getRsRestaurant().getRsName())  // 식당 이름 추가
                        .build())
                .collect(Collectors.toList());
    }

    // 특정 메뉴의 세부 정보를 반환하는 메서드
    public RsMenusDTO getMenuDetails(Long menuId) {
        // menuId에 해당하는 메뉴를 찾아서 DTO로 변환
        return menuRepository.findById(menuId)
                .map(menu -> RsMenusDTO.builder()
                        .menuId(menu.getRsMenuId())
                        .rsId(menu.getRsRestaurant().getRsId())
                        .menuName(menu.getRsMenuName())
                        .menuDesc(menu.getRsMenuDesc())
                        .menuPrice(menu.getRsMenuPrice())
                        .menuState(menu.getRsMenuState())
                        .menuAppear(menu.getRsMenuAppear())
                        .isFeatured(menu.isFeatured())
                        .menuPhotoOrigin(menu.getRsMenuPhotoOrigin())
                        .menuPhotoPath(menu.getRsMenuPhotoPath())
                        .menuPhotoName(menu.getRsMenuPhotoName())
                        .menuCreatedAt(menu.getRsMenuCreatedAt())
                        .menuUpdatedAt(menu.getRsMenuUpdatedAt())
                        .menuDeletedAt(menu.getRsMenuDeletedAt())
                        .rsName(menu.getRsRestaurant().getRsName())  // 식당 이름 추가
                        .build())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + menuId)); // 메뉴가 없으면 예외 발생
    }
}
