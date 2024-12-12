package com.project.eatTogether.application.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userId;                // 유저 id
    private String userEmail;           // 유저 이메일
    private String userName;            // 유저 이름
    private String userPw;              // 유저 pw
    private String userPhone;           // 유저 연락처
    private String userState;           // 유저 상태
    private String userNickName;        // 유저 닉넴
    private Date userBirthday;          // 유저 생일
    private String userGender;          // 유저 성별
    private String userPhotoOrigin;     // 유저 프로필사진원본병
    private String userPhotoPath;       // 유저 프로필사진경로명
    private String userPhotoName;       // 유저 프로필사진이름
    private String userGrade;           // 유저 등급
    private int userFollower;           // 유저 팔로워수
    private int userFollowing;          // 유저 팔로잉수
    private String userAuth;            // 유저 유저권한
    private LocalDateTime userCreatedAt;// 유저 가입입
    private LocalDateTime userUpdatedAt;// 유저 수정일
    private LocalDateTime userDeletedAt;// 유저 탈퇴일
}
