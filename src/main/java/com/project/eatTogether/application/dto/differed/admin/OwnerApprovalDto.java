package com.project.eatTogether.application.dto.differed.admin;

import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.domain.entity.differed.Restaurant;
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
    private String businessRegistrationNumber;
    private LocalDateTime registeredAt;
    private OwnerStatus status;
    private String statusName;
    private String holdReason;

    //식당 정보
    private Long restaurantId;
    private String restaurantName;
    private String restaurantPhone;
    private String restaurantAddress;
    private String openingHours;
    private String parkingInfo;


    public static OwnerApprovalDto convertToDto(Member member) {
        // member.getRestaurants()는 List이므로 첫 번째 식당 정보를 가져옴
        Restaurant restaurant = member.getRestaurants().isEmpty() ?
                null : member.getRestaurants().get(0);

        return OwnerApprovalDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .businessRegistrationNumber(member.getBusinessRegistrationNumber())
                .status(member.getOwnerStatus())
                .statusName(member.getOwnerStatus().getStatus())
                .registeredAt(member.getCreatedAt())
                //식당정보
                .restaurantId(restaurant != null ? restaurant.getId() : null)
                .restaurantName(restaurant != null ? restaurant.getName() : null)
                .restaurantPhone(restaurant != null ? restaurant.getPhone() : null)
                .restaurantAddress(restaurant != null ? restaurant.getAddress().getFullAddress() : null)
                .openingHours(restaurant != null ? restaurant.getOpeningHours() : null)
                .parkingInfo(restaurant != null ? restaurant.getParkingInfo() : null)
                .build();
    }
}
