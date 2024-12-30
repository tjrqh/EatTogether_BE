package com.project.eatTogether.application.dto.userDto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserSignUpDto {
    @Email(message = "올바른 이메일 형식이어야 합니다")
    @NotBlank(message = "이메일은 필수 입력값입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다")
    private String name;

    @NotBlank(message = "닉네임은 필수 입력값입니다")
    private String nickname;

    @NotBlank(message = "전화번호는 필수 입력값입니다")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",
            message = "올바른 전화번호 형식이어야 합니다")
    private String phone;

    @NotNull(message = "생년월일은 필수 입력값입니다")
    @Past(message = "생년월일은 과거 날짜여야 합니다")
    private LocalDate birthday;

    @NotBlank(message = "성별은 필수 입력값입니다")
    @Pattern(regexp = "^(MALE|FEMALE)$",
            message = "성별은 MALE 또는 FEMALE이어야 합니다")
    private String gender;
}
