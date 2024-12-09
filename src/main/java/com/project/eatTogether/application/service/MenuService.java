package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsMenusDTO;
import com.project.eatTogether.infrastructure.RsMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
                        .build())
                .collect(Collectors.toList());
    }
}
