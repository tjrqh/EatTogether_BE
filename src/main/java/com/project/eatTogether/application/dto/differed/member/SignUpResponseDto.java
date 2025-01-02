    package com.project.eatTogether.application.dto.differed.member;

    import com.project.eatTogether.domain.entity.differed.Member;
    import com.project.eatTogether.domain.enums.MemberRole;
    import lombok.Builder;
    import lombok.Getter;

    @Getter
    public class SignUpResponseDto {
        private final Long id;
        private final String email;
        private final String name;
        private final String role;
        private final String status;

        @Builder
        public SignUpResponseDto(Long id, String email, String name, String role, String status) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.role = role;
            this.status = status;
        }

        public static SignUpResponseDto from(Member member) {
            String status = member.getRole() == MemberRole.OWNER
                    ? member.getOwnerStatus().getStatus()  // 점주인 경우 ownerStatus의 status 값
                    : member.getMemberStatus().toString(); // 일반 회원인 경우 memberStatus

            return SignUpResponseDto.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .role(member.getRole().toString())
                    .status(status)
                    .build();
        }
    }