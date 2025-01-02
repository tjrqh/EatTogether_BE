package com.project.eatTogether.application.dto.differed.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerSignUpDto {
    @Email(message = "올바른 이메일 형식이어야 합니다")
    @NotBlank(message = "이메일은 필수 입력값입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다")
    private String ownerName;

    @NotBlank(message = "점주 연락처는 필수 입력값입니다")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",
            message = "올바른 전화번호 형식이어야 합니다")
    private String ownerPhone;

    @NotBlank(message = "식당 연락처는 필수 입력값입니다")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",
            message = "올바른 전화번호 형식이어야 합니다")
    private String restaurantPhone;

    @NotBlank(message = "사업자등록번호는 필수 입력값입니다")
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$",
            message = "올바른 사업자등록번호 형식이어야 합니다")
    private String businessRegistrationNumber;

    @NotBlank(message = "식당 이름은 필수 입력값입니다")
    private String restaurantName;

    @NotBlank(message = "도로명 주소는 필수 입력값입니다")
    private String streetAddress;

    @NotBlank(message = "상세 주소는 필수 입력값입니다")
    private String detailAddress;

    @NotBlank(message = "우편번호는 필수 입력값입니다")
    private String postcode;

    @NotNull(message = "음식 카테고리는 필수 입력값입니다")
    private Long categoryId;  // CuisineCategories의 ID


    private MultipartFile businessLicense;

    private String uploadFileNames;


}