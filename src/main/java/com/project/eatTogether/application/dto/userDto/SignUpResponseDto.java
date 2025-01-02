    package com.project.eatTogether.application.dto.userDto;

    import com.project.eatTogether.domain.entity.User;
    import com.project.eatTogether.domain.enums.UserRole;
    import lombok.Builder;
    import lombok.Getter;

    @Getter
    public class SignUpResponseDto {
        private final Long userId;
        private final String userEmail;
        private final String userName;
        private final String userRole;
        private final String userStatus;

        @Builder
        public SignUpResponseDto(Long userId, String userEmail, String userName, String userRole, String userStatus) {
            this.userId = userId;
            this.userEmail = userEmail;
            this.userName = userName;
            this.userRole = userRole;
            this.userStatus = userStatus;
        }

            public static SignUpResponseDto from(User user) {
                String status = user.getUserRole() == UserRole.OWNER
                        ? user.getOwnerStatus().getStatus()  // 점주인 경우 ownerStatus의 status 값
                        : user.getUserStatus().toString(); // 일반 회원인 경우 memberStatus

                return SignUpResponseDto.builder()
                        .userId(user.getUserId())
                        .userEmail(user.getUserEmail())
                        .userName(user.getUserName())
                        .userRole(user.getUserRole().toString())
                        .userStatus(status)
                        .build();
            }
        }