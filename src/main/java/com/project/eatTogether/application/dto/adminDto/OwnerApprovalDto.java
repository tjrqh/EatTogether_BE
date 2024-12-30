package com.project.eatTogether.application.dto.adminDto;

import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.User;
import com.project.eatTogether.domain.enums.OwnerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerApprovalDto {

    //점주 정보
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String rsDocumentBusinessId;
    private LocalDateTime registeredAt;
    private OwnerStatus status;
    private String statusName;
    private String holdReason;

    //식당 정보
    private Long restaurantId;
    private String restaurantName;
    private String restaurantPhone;
    private String restaurantAddress;
    private String rsTime;


    public static OwnerApprovalDto convertToDto(User user) {
        // member.getRestaurants()는 List이므로 첫 번째 식당 정보를 가져옴
        RsRestaurant restaurant = user.getRestaurants().isEmpty() ?
                null : user.getRestaurants().get(0);

        return OwnerApprovalDto.builder()
                .id(user.getUserId())
                .name(user.getUserName())
                .email(user.getUserEmail())
                .phone(user.getUserPhone())
                .rsDocumentBusinessId(user.getRsDocumentBusinessId())
                .status(user.getOwnerStatus())
                .statusName(user.getOwnerStatus().getStatus())
                .registeredAt(user.getCreatedAt())
                //식당정보
                .restaurantId(restaurant != null ? restaurant.getRsId() : null)
                .restaurantName(restaurant != null ? restaurant.getRsName() : null)
                .restaurantPhone(restaurant != null ? restaurant.getRsPhone() : null)
                .restaurantAddress(restaurant != null ? restaurant.getAddress().getFullAddress() : null)
                .rsTime(restaurant != null ? restaurant.getRsTime() : null)
                .build();
    }
}
