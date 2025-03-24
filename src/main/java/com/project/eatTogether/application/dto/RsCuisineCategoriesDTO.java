    package com.project.eatTogether.application.dto;

    import com.project.eatTogether.domain.entity.RsCuisineCategories;
    import com.project.eatTogether.domain.entity.RsLocationCategories;
    import com.project.eatTogether.domain.entity.RsRestaurant;
    import jakarta.persistence.Column;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class RsCuisineCategoriesDTO {
        private String restaurantAddr; // 위치 이름 (기존 rsLocationName 대체)
        private String rsCuisineCategoryName; // 식당 종류 이름
        private String cuisineType;            // CuisineType enum의 displayName
        private Long rsId; // 식당 ID
        private String rsName; // 식당 이름
        private Double rsAvgRate; // 식당 평균 평점
        private String rsInfo; // 식당 정보
        private float restaurantLat; // 식당 위도
        private float restaurantLong; // 식당 경도
        private int rsBookmarkCount; // 즐겨찾기 개수 (추가)
        private int rsReviewCount; // 리뷰 개수 (추가)

        @Builder.Default
        private boolean rsQueueEnabled = true;  // 기본값 true, 줄서기 가능

        @Builder.Default
        private boolean isPrepaid = false;

        // RsCuisineCategories 엔티티를 위한 메서드
        public static RsCuisineCategoriesDTO fromCuisineCategory(RsCuisineCategories category) {
            if (category == null) {
                return null;
            }

            RsRestaurant restaurant = (RsRestaurant) category.getRsRestaurants();

            return RsCuisineCategoriesDTO.builder()
                    .restaurantAddr(restaurant.getAddress().getFullAddress())
                    .cuisineType(category.getType().getDisplayName())
                    .rsId(restaurant.getRsId())
                    .rsName(restaurant.getRsName())
                    .rsAvgRate(restaurant.getRsAvgRate())
                    .rsInfo(restaurant.getRsInfo())
                    .restaurantLat(restaurant.getAddress().getLatitude())
                    .restaurantLong(restaurant.getAddress().getLongitude())
                    .rsBookmarkCount(restaurant.getRsBookmarkCount())
                    .rsReviewCount(restaurant.getRsReviewCount())
                    .rsQueueEnabled(restaurant.isRsQueueEnabled())
                    .isPrepaid(restaurant.isPrepaid())
                    .build();
        }

        // RsRestaurant 엔티티를 위한 메서드
        public static RsCuisineCategoriesDTO fromRestaurant(RsRestaurant restaurant) {
            if (restaurant == null || restaurant.getRsCuisineCategories() == null) {
                return null;
            }

            return RsCuisineCategoriesDTO.builder()
                    .restaurantAddr(restaurant.getAddress().getFullAddress())
                    .cuisineType(restaurant.getRsCuisineCategories().getType().getDisplayName())
                    .rsId(restaurant.getRsId())
                    .rsName(restaurant.getRsName())
                    .rsAvgRate(restaurant.getRsAvgRate())
                    .rsInfo(restaurant.getRsInfo())
                    .restaurantLat(restaurant.getAddress().getLatitude())
                    .restaurantLong(restaurant.getAddress().getLongitude())
                    .rsBookmarkCount(restaurant.getRsBookmarkCount())
                    .rsReviewCount(restaurant.getRsReviewCount())
                    .rsQueueEnabled(restaurant.isRsQueueEnabled())
                    .isPrepaid(restaurant.isPrepaid())
                    .build();
        }
    }
