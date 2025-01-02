package com.project.eatTogether.presentation.controller.differed;

import com.project.eatTogether.application.dto.differed.login.LoginRequestDto;
import com.project.eatTogether.application.dto.differed.member.OwnerSignUpDto;
import com.project.eatTogether.application.dto.differed.member.SignUpResponseDto;
import com.project.eatTogether.application.dto.differed.member.UserSignUpDto;
import com.project.eatTogether.application.service.differed.MemberService;
import com.project.eatTogether.application.service.differed.RestaurantService;
import com.project.eatTogether.infrastructure.util.CustomFileUtil;
import com.project.eatTogether.infrastructure.util.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final RestaurantService restaurantService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CustomFileUtil fileUtil;


    /** 일반 사용자 회원가입 */
    @PostMapping("/signup/user")
    public ResponseEntity<SignUpResponseDto> signUpUser(
            @Valid @RequestBody UserSignUpDto requestDto
    ) {
        SignUpResponseDto responseDto = memberService.signUpUser(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    /** 점주 회원가입 */
    @PostMapping("/signup/owner")
    public ResponseEntity<SignUpResponseDto> signUpOwner(
            @Valid @ModelAttribute OwnerSignUpDto requestDto) {

        MultipartFile businessLicense = requestDto.getBusinessLicense();
        String uploadFileName = fileUtil.saveFile(businessLicense); // 파일 저장
        requestDto.setUploadFileNames(uploadFileName); // 저장된 파일 이름 설정

        // 회원가입 및 레스토랑 생성을 포함하는 서비스 메서드 호출
        SignUpResponseDto responseDto = memberService.signUpOwner(requestDto, uploadFileName);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    /** 전체 회원 조회 */
    @GetMapping("/all")
    public ResponseEntity<List<SignUpResponseDto>> getAllMembers() {
        List<SignUpResponseDto> members = memberService.findAllMember();
        return ResponseEntity.ok(members);
    }

    //특정 회원 조회
//    @GetMapping("/getuser")
//    public ResponseEntity<?> getMember() {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            return ResponseEntity.status(HttpStatus.OK).body();
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    /** 로그인 */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String password) {
        try {
            // LoginRequestDto 대신 직접 파라미터를 사용
            LoginRequestDto loginRequestDto = new LoginRequestDto(email, password);
            Object loginResponse = memberService.login(loginRequestDto);
            return ResponseEntity.ok(loginResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }


    //로그아웃
    @PostMapping("/signout")
    public ResponseEntity<?> signOut() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body("로그아웃에 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //회원수정
    @PutMapping("/modify")
    public ResponseEntity<?> modifyUser() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body("회원수정에 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /** 이메일 중복 확인 */
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String email) {
        boolean exists = memberService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    /** 닉네임 중복 확인 */
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@RequestParam String nickname) {
        boolean exists = memberService.existsByNickname(nickname);
        return ResponseEntity.ok(exists);
    }

    /** 사업자등록번호 중복 확인 */
    @GetMapping("/check-business-number")
    public ResponseEntity<Boolean> checkBusinessNumberDuplicate(
            @RequestParam String businessNumber
    ) {
        boolean exists = memberService.existsByBusinessRegistrationNumber(businessNumber);
        return ResponseEntity.ok(exists);
    }

    /** Access Token 재발급 */
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshAccessToken(
            @RequestHeader("Refresh-Token") String refreshToken
    ) {
        String newAccessToken = memberService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok("Bearer " + newAccessToken);
    }
}


