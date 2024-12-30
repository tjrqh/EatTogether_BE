package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsMenusDTO;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantMenuReadResponse;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantMenuUpdateRequest;
import com.project.eatTogether.domain.entity.RsMenus;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.infrastructure.RsMenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
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
                        .createdAt(menu.getCreatedAt())       // 변경
                        .modifiedAt(menu.getModifiedAt())     // 변경
                        .deletedAt(menu.getDeletedAt())       // 변경
                        .rsName(menu.getRsRestaurant().getRsName())
                        .build())
                .collect(Collectors.toList());
    }

    // 특정 메뉴의 세부 정보를 반환하는 메서드
    public RsMenusDTO getMenuDetails(Long menuId) {
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
                        .createdAt(menu.getCreatedAt())       // 변경
                        .modifiedAt(menu.getModifiedAt())     // 변경
                        .deletedAt(menu.getDeletedAt())       // 변경
                        .rsName(menu.getRsRestaurant().getRsName())
                        .build())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + menuId));
    }

    // owner 페이지 메뉴 불러오기
    public List<RestaurantMenuReadResponse> getOwnerMenuByRsId(Long rsId) {
        return menuRepository.findByRsId(rsId).stream().map(menu ->
                RestaurantMenuReadResponse.builder()
                        .id(menu.getRsMenuId())
                        .name(menu.getRsMenuName())
                        .price(menu.getRsMenuPrice())
                        .description(menu.getRsMenuDesc())
                        .category(menu.getRsMenuState())
                        .build()).collect(Collectors.toList());
    }

    // 오너페이지 메뉴 수정
    public ResponseEntity<HttpStatus> getMenuUpdateRequest(RestaurantMenuUpdateRequest restaurantMenuUpdateRequest) {
        Optional<RsMenus> menus = menuRepository.findById(restaurantMenuUpdateRequest.getId());
        RsMenus rsMenus = menus.orElseThrow(
                () -> new NoSuchElementException("Search Not Found : " + restaurantMenuUpdateRequest.getId()));
        rsMenus.setRsMenuName(restaurantMenuUpdateRequest.getName());
        rsMenus.setRsMenuDesc(restaurantMenuUpdateRequest.getDescription());
        rsMenus.setRsMenuPrice(restaurantMenuUpdateRequest.getPrice());
        rsMenus.setRsMenuState(restaurantMenuUpdateRequest.getCategory());
        menuRepository.save(rsMenus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 오너페이지 메뉴 추가
    public ResponseEntity<HttpStatus> menuCreateRequest(RestaurantMenuUpdateRequest restaurantMenuUpdateRequest,Long id) {
        RsMenus menus = new RsMenus();
        RsRestaurant restaurant = new RsRestaurant();
        restaurant.setRsId(id);
        menus.setRsMenuName(restaurantMenuUpdateRequest.getName());
        menus.setRsMenuDesc(restaurantMenuUpdateRequest.getDescription());
        menus.setRsMenuPrice(restaurantMenuUpdateRequest.getPrice());
        menus.setRsMenuState(restaurantMenuUpdateRequest.getCategory());
        menus.setRsRestaurant(restaurant);
        menus.setRsMenuAppear("Y");
        menus.setFeatured(true);

        menuRepository.save(menus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 오너페이지 메뉴 삭제
    @Transactional
    public ResponseEntity<HttpStatus> menuDeleteRequest(Long id) {
        RsMenus menus = menuRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Menu not found with id: " + id));
        menus.delete();  // BaseEntity의 delete() 메서드 사용
        menuRepository.save(menus);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
