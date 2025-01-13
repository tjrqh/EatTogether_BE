    package com.project.eatTogether.application.dto;

    import com.project.eatTogether.domain.entity.RsCuisineCategories;
    import com.project.eatTogether.domain.entity.RsRestaurant;
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
        private String restaurantAddr;
        private String cuisineType;
        private Long rsId;
        private String rsName;
        private Double rsAvgRate;
        private String rsInfo;
        private float restaurantLat;
        private float restaurantLong;
        private int rsBookmarkCount;
        private int rsReviewCount;

        @Builder.Default
        private boolean rsQueueEnabled = true;

        @Builder.Default
        private boolean isPrepaid = false;

        // 카테고리에 속한 레스토랑 목록을 반환하는 메서드
        public static List<RsCuisineCategoriesDTO> fromCuisineCategory(RsCuisineCategories category) {
            if (category == null) {
                return null;
            }

            return category.getRsRestaurants().stream()
                    .map(restaurant -> RsCuisineCategoriesDTO.builder()
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
                            .build())
                    .toList();
        }

        // 단일 레스토랑에 대한 메서드는 그대로 유지
        public static RsCuisineCategoriesDTO fromRestaurant(RsRestaurant restaurant) {
            if (restaurant == null || restaurant.getRsCuisineCategories() == null) {
                return null;
            }

            return RsCuisineCategoriesDTO.builder()
                    .restaurantAddr(restaurant.getAddress().getFullAddress())
                    .cuisineType(restaurant.getRsCuisineCategories().getType().getDisplayName()) // cuisineType 추가
                    .rsId(restaurant.getRsId())
                    .rsName(restaurant.getRsName())
                    .rsAvgRate(restaurant.getRsAvgRate())
                    .rsInfo(restaurant.getRsInfo())
//                    .restaurantLat(restaurant.getAddress().getLatitude())
//                    .restaurantLong(restaurant.getAddress().getLongitude())
                    .rsBookmarkCount(restaurant.getRsBookmarkCount())
                    .rsReviewCount(restaurant.getRsReviewCount())
                    .rsQueueEnabled(restaurant.isRsQueueEnabled())
                    .isPrepaid(restaurant.isPrepaid())
                    .build();
        }
    }
