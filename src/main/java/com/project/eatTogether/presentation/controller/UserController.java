//package com.project.eatTogether.presentation.controller;
//
//import com.project.eatTogether.application.dto.adminDto.UserManagingReadResponse;
//import java.util.List;
//
//import com.project.eatTogether.application.dto.loginDto.LoginRequestDto;
//import com.project.eatTogether.application.dto.userDto.OwnerSignUpDto;
//import com.project.eatTogether.application.dto.userDto.RefreshTokenResponse;
//import com.project.eatTogether.application.dto.userDto.SignUpResponseDto;
//import com.project.eatTogether.application.dto.userDto.UserSignUpDto;
//import com.project.eatTogether.application.service.userService.UserService;
//import com.project.eatTogether.infrastructure.util.CustomFileUtil;
//import com.project.eatTogether.infrastructure.util.JWTUtil;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/api/user")
//@RequiredArgsConstructor
//public class UserController {
//
//  private final UserService userService;
//  private final AuthenticationManager authenticationManager;
//  private final JWTUtil jwtUtil;
//  private final CustomFileUtil fileUtil;
//
//
////  @GetMapping("")
////  public List<UserManagingReadResponse> getUserManagingSearch(@RequestParam String userNickname){
////    return userService.getUserManagingSearch(userNickname);
////  }
////
////  @PutMapping("/{id}")
////  public ResponseEntity<String> sanctionUser (@PathVariable Long id){
////    id = 1L;
////    return userService.sanctionUserService(id);
////  }
//
//  /** 일반 사용자 회원가입 */
//  @PostMapping("/signup/user")
//  public ResponseEntity<SignUpResponseDto> signUpUser(
//          @Valid @RequestBody UserSignUpDto requestDto
//  ) {
//    SignUpResponseDto responseDto = userService.signUpUser(requestDto);
//    return ResponseEntity
//            .status(HttpStatus.CREATED)
//            .body(responseDto);
//  }
//
//  /** 점주 회원가입 */
//  @PostMapping("/signup/owner")
//  public ResponseEntity<SignUpResponseDto> signUpOwner(
//          @Valid @ModelAttribute OwnerSignUpDto requestDto) {
//
//    MultipartFile businessLicense = requestDto.getBusinessLicense();
//    String uploadFileName = fileUtil.saveFile(businessLicense); // 파일 저장
//    requestDto.setUploadFileNames(uploadFileName); // 저장된 파일 이름 설정
//
//    // 회원가입 및 레스토랑 생성을 포함하는 서비스 메서드 호출
//    SignUpResponseDto responseDto = userService.signUpOwner(requestDto, uploadFileName);
//
//    return ResponseEntity
//            .status(HttpStatus.CREATED)
//            .body(responseDto);
//  }
//
//  /** 전체 회원 조회 */
//  @GetMapping("/all")
//  public ResponseEntity<List<SignUpResponseDto>> getAllMembers() {
//    List<SignUpResponseDto> members = userService.findAllMember();
//    return ResponseEntity.ok(members);
//  }
//
//  //특정 회원 조회
////    @GetMapping("/getuser")
////    public ResponseEntity<?> getMember() {
////        try {
////            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////            return ResponseEntity.status(HttpStatus.OK).body();
////        } catch (IllegalArgumentException e) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
////        }
////    }
//
//  /** 로그인 */
//  @PostMapping("/login")
//  public ResponseEntity<?> login(
//          @RequestParam String email,
//          @RequestParam String password) {
//    try {
//      // LoginRequestDto 대신 직접 파라미터를 사용
//      LoginRequestDto loginRequestDto = new LoginRequestDto(email, password);
//      Object loginResponse = userService.login(loginRequestDto);
//      return ResponseEntity.ok(loginResponse);
//    } catch (IllegalArgumentException e) {
//      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//              .body(e.getMessage());
//    } catch (IllegalStateException e) {
//      return ResponseEntity.status(HttpStatus.FORBIDDEN)
//              .body(e.getMessage());
//    }
//  }
//
//
//  //로그아웃
//  @PostMapping("/signout")
//  public ResponseEntity<?> signOut() {
//    try {
//      return ResponseEntity.status(HttpStatus.CREATED).body("로그아웃에 성공했습니다.");
//    } catch (Exception e) {
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//  }
//
//  //회원수정
//  @PutMapping("/modify")
//  public ResponseEntity<?> modifyUser() {
//    try {
//      return ResponseEntity.status(HttpStatus.CREATED).body("회원수정에 성공했습니다.");
//    } catch (Exception e) {
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//  }
//
//  /** 이메일 중복 확인 */
//  @GetMapping("/check-email")
//  public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String userEmail) {
//    boolean exists = userService.existsByUserEmail(userEmail);
//    return ResponseEntity.ok(exists);
//  }
//
//  /** 닉네임 중복 확인 */
//  @GetMapping("/check-nickname")
//  public ResponseEntity<Boolean> checkNicknameDuplicate(@RequestParam String nickname) {
//    boolean exists = userService.existsByNickname(nickname);
//    return ResponseEntity.ok(exists);
//  }
//
//  /** 사업자등록번호 중복 확인 */
//  @GetMapping("/check-business-number")
//  public ResponseEntity<Boolean> checkBusinessNumberDuplicate(
//          @RequestParam String businessNumber
//  ) {
//    boolean exists = userService.existsByBusinessRegistrationNumber(businessNumber);
//    return ResponseEntity.ok(exists);
//  }
//
//  /** Access Token 재발급 */
//  @PostMapping("/refresh-token")
//  public ResponseEntity<?> refreshAccessToken(@RequestHeader("Refresh-Token") String refreshToken) {
//    try {
//      // Bearer 제거 로직 추가
//      String jwtRefreshToken = refreshToken.replace("Bearer ", "");
//      System.out.println("Extracted Refresh Token: " + jwtRefreshToken);
//      String newAccessToken = userService.refreshAccessToken(jwtRefreshToken);
//
//      return ResponseEntity.status(HttpStatus.OK).body(RefreshTokenResponse.builder()
//              .newToken("Bearer "+ newAccessToken)
//              .build());
//    } catch (Exception e) {
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다." + e.getMessage());
//    }
//  }
//}
