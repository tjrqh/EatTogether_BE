//package com.project.eatTogether.application.service.userService;
//
//import com.project.eatTogether.application.dto.adminDto.UserManagingReadResponse;
//import com.project.eatTogether.domain.entity.User;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import com.project.eatTogether.infrastructure.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UserService {
//
//  private final UserRepository userRepository;
//
//  public ResponseEntity<String> userStateSanctionService(Long id) {
//    Optional<User> user = userRepository.findById(id);
//    User findByUser = user.orElseThrow(() ->
//        new NoSuchElementException("Search Declare Not Found : " + id));
//    int userState = Integer.parseInt(findByUser.getUserState());
//    int sanction = 1 + userState;
//    findByUser.setUserState(String.valueOf(sanction));
//    userRepository.save(findByUser);
//
//    return ResponseEntity.ok("Ok");
//  }
//
//  public List<UserManagingReadResponse> getUserManagingSearch(String userNickName) {
//    try {
//      if (userNickName.trim().isEmpty()) {
//        return Collections.emptyList();
//      }
//      List<User> userList = userRepository.findByUserNickname(userNickName);
//
//      return userList.stream()
//              .map(user -> UserManagingReadResponse.builder()
//                      .id(user.getUserId())
//                      .userName(user.getUserName())
//                      .userNickName(user.getUserNickname())
//                      .userState(user.getUserState())
//                      .deletedAt(user.getDeletedAt() != null ?
//                              user.getDeletedAt().toString() : null)  // BaseEntity의 deletedAt 사용
//                      .build())
//              .collect(Collectors.toList());
//    } catch (Exception e) {
//      log.error("Error searching users by nickname: {}", e.getMessage(), e);
//      throw new RuntimeException("Error occurred while searching users", e);
//    }
//  }
//
//
//  public ResponseEntity<String> sanctionUserService(Long id) {
//    try {
//      User findByUser = userRepository.findById(id)
//              .orElseThrow(() -> new NoSuchElementException("User Not Found : " + id));
//
//      findByUser.delete();  // BaseEntity의 delete() 메서드 사용
//      userRepository.save(findByUser);
//
//      return ResponseEntity.ok("Ok");
//    } catch (Exception e) {
//      log.error("Error sanctioning user: {}", e.getMessage(), e);
//      throw new RuntimeException("Error occurred while sanctioning user", e);
//    }
//  }
//}

package com.project.eatTogether.application.service.userService;

import com.project.eatTogether.application.dto.loginDto.LoginRequestDto;
import com.project.eatTogether.application.dto.loginDto.OwnerLoginResponseDto;
import com.project.eatTogether.application.dto.loginDto.UserLoginResponseDto;
import com.project.eatTogether.application.dto.userDto.OwnerSignUpDto;
import com.project.eatTogether.application.dto.userDto.SignUpResponseDto;
import com.project.eatTogether.application.dto.userDto.UserSignUpDto;
import com.project.eatTogether.application.service.EntityValidationService;
import com.project.eatTogether.application.service.FileService;
import com.project.eatTogether.application.service.TokenService;
import com.project.eatTogether.domain.Address;
import com.project.eatTogether.domain.entity.RsCuisineCategories;
import com.project.eatTogether.domain.entity.RsDocument;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.User;
import com.project.eatTogether.domain.enums.OwnerStatus;
import com.project.eatTogether.domain.enums.UserRole;
import com.project.eatTogether.domain.enums.UserStatus;
import com.project.eatTogether.infrastructure.CuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.UserRepository;
import com.project.eatTogether.infrastructure.RestaurantRepository;
import com.project.eatTogether.infrastructure.util.JWTUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.project.eatTogether.domain.enums.RestaurantStatus.CLOSED;
import static com.project.eatTogether.domain.enums.RestaurantStatus.OPEN;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final RestaurantRepository restaurantRepository;
  private final CuisineCategoriesRepository cuisineCategoriesRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JWTUtil jwtUtil;
  private final EntityValidationService entityValidationService;
  private final TokenService tokenService;
  private final FileService fileService;

  public ResponseEntity<String> userStateSanctionService(Long id) {
    Optional<User> user = userRepository.findById(id);
    User findByUser = user.orElseThrow(() ->
        new NoSuchElementException("Search Declare Not Found : " + id));
    int userState = Integer.parseInt(findByUser.getUserState());
    int sanction = 1 + userState;
    findByUser.setUserState(String.valueOf(sanction));
    userRepository.save(findByUser);

    return ResponseEntity.ok("Ok");
  }


  /** 일반 사용자 회원가입*/
  @Transactional
  public SignUpResponseDto signUpUser(UserSignUpDto requestDto) {
    //이메일 중복확인
    if (userRepository.existsByUserEmail(requestDto.getEmail())) {
      throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
    }

    //닉네임 중복확인
    if (userRepository.existsByUserNickname(requestDto.getNickname())) {
      throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
    }

    String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

    User member = User.createUser(
            requestDto.getEmail(),
            encodedPassword,
            requestDto.getName(),
            requestDto.getNickname(),
            requestDto.getPhone(),
            requestDto.getBirthday(),
            requestDto.getGender()
    );


    User savedMember = userRepository.save(member);
    return SignUpResponseDto.from(savedMember);
  }

  /** 점주 회원가입*/
  @Transactional
  public SignUpResponseDto signUpOwner(OwnerSignUpDto dto, String uploadFileName) {
    // 이메일 중복 확인
    if (userRepository.existsByUserEmail(dto.getEmail())) {
      throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
    }

    // 사업자등록번호 중복 확인
    if (userRepository.existsByRsDocumentBusinessId(dto.getRsDocumentBusinessId())) {
      throw new IllegalArgumentException("이미 등록된 사업자등록번호입니다.");
    }

    // 사업자등록증 파일 저장
    MultipartFile businessLicense = dto.getBusinessLicense();
    String savedFileName = fileService.saveFile(businessLicense, "business-license");
    String filePath = fileService.getFilePath(savedFileName);

    // Member 생성 및 저장
    User owner = User.createOwner(
            dto.getEmail(),
            passwordEncoder.encode(dto.getPassword()),
            dto.getOwnerName(),
            dto.getOwnerPhone(),
            dto.getRsDocumentBusinessId(),
            filePath,
            savedFileName
    );
    User savedOwner = userRepository.save(owner);

    // Document 생성
    RsDocument document = RsDocument.builder()
            .rsDocumentBusinessId(dto.getRsDocumentBusinessId())
            .documentPath(filePath)
            .documentName(savedFileName)
            .build();

    // 주소 생성
    Address address = new Address(
            dto.getStreetAddress(),
            dto.getDetailAddress(),
            dto.getPostcode(),
            null,
            null
    );

    // 레스토랑 생성 - 팩토리 메서드 사용
    RsRestaurant restaurant = RsRestaurant.createRestaurant(
            dto.getRestaurantName(),
            dto.getRestaurantPhone(),
            savedOwner,
            address,
            document,
            null  // CuisineCategories는 별도로 설정
    );

    // 카테고리 조회 및 설정
    RsCuisineCategories cuisineCategory = cuisineCategoriesRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("해당 음식 카테고리를 찾을 수 없습니다"));
    restaurant.setCuisineCategory(cuisineCategory);

    restaurantRepository.save(restaurant);

    return SignUpResponseDto.from(savedOwner);
  }

  /** 모든 회원 조회*/
  public List<SignUpResponseDto> findAllMember() {
    List<User> result = userRepository.findAll();
    return result.stream()
            .map(SignUpResponseDto::from)
            .collect(Collectors.toList());
  }

  /** 이메일로 회원 조회*/
  public User getMemberByUserEmail(String userEmail) {
    return userRepository.findByUserEmail(userEmail)
            .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));
  }

  /**로그인*/
  @Transactional(readOnly = true)
  public Object login(LoginRequestDto dto) {
    User user = userRepository.findByUserEmail(dto.getUserEmail())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

    if (!passwordEncoder.matches(dto.getUserPassword(), user.getUserPw())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    String accessToken = tokenService.generateAccessToken(user.getUserEmail());
    String refreshToken = tokenService.generateRefreshToken(user.getUserEmail());

    // 점주 회원인 경우
    if (user.getRole() == UserRole.OWNER) {
      if (user.getOwnerStatus() != OwnerStatus.APPROVED) {
        throw new IllegalStateException("아직 관리자 승인이 완료되지 않았습니다. " +
                user.getOwnerStatus().getMessage());
      }

      RsRestaurant restaurant = restaurantRepository.findByOwner(user)
              .orElseThrow(() -> new IllegalStateException("식당 정보를 찾을 수 없습니다."));

      return OwnerLoginResponseDto.builder()
              .accessToken("Bearer " + accessToken)
              .refreshToken("Bearer " + refreshToken)
              .userEmail(user.getUserEmail())
              .userName(user.getUserName())
              .userRole(user.getUserRole().getKey())
              .rsDocumentBusinessId(user.getRsDocumentBusinessId())
              .rsName(restaurant.getRsName())
              .ownerStatus(user.getOwnerStatus())
              .build();
    }

    // 일반 회원인 경우
    if (user.getUserStatus() != UserStatus.ACTIVE) {
      throw new IllegalStateException("활성화되지 않은 계정입니다.");
    }

    return UserLoginResponseDto.builder()
            .accessToken("Bearer " + accessToken)
            .refreshToken("Bearer " + refreshToken)
            .userEmail(user.getUserEmail())
            .userNickname(user.getUserNickname())
            .userName(user.getUserName())
            .userRole(user.getUserRole().getKey())
            .build();
  }

  @Transactional(readOnly = true)
  public List<User> getPendingOwners() {
    return userRepository.findByRoleAndOwnerStatus(UserRole.OWNER, OwnerStatus.PENDING);
  }

  @Transactional
  public void approveOwner(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

    if (user.getRole() != UserRole.OWNER) {
      throw new IllegalArgumentException("점주 회원이 아닙니다.");
    }

    // 점주 승인
    user.approveOwner();

    // 해당 점주의 식당 찾기
    RsRestaurant restaurant = restaurantRepository.findByOwner(user)
            .orElseThrow(() -> new IllegalArgumentException("식당 정보를 찾을 수 없습니다."));

    // 식당 상태 변경
    restaurant.setStatus(OPEN);
  }

  @Transactional
  public void rejectOwner(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

    if (user.getRole() != UserRole.OWNER) {
      throw new IllegalArgumentException("점주 회원이 아닙니다.");
    }

    // 점주 거절
    user.rejectOwner();

    // 해당 점주의 식당 찾기
    RsRestaurant restaurant = restaurantRepository.findByOwner(user)
            .orElseThrow(() -> new IllegalArgumentException("식당 정보를 찾을 수 없습니다."));

    // 식당 상태 변경
    restaurant.setStatus(CLOSED);
  }

  @Transactional
  public void suspendOwner(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

    if (user.getRole() != UserRole.OWNER) {
      throw new IllegalArgumentException("점주 회원이 아닙니다.");
    }

    // 점주 자격 정지
    user.suspendOwner();

    // 해당 점주의 식당 찾기
    RsRestaurant restaurant = restaurantRepository.findByOwner(user)
            .orElseThrow(() -> new IllegalArgumentException("식당 정보를 찾을 수 없습니다."));

    // 식당 상태 변경
    restaurant.setStatus(CLOSED);
  }

  @Transactional(readOnly = true)
  public List<RsRestaurant> getRestaurantsByMemberId(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

    if (user.getRole() != UserRole.OWNER) {
      throw new IllegalArgumentException("식당 점주만 조회할 수 있습니다.");
    }

    return user.getRestaurants();
  }

  /** Access Token 재발급 */
  @Transactional(readOnly = true)
  public String refreshAccessToken(String refreshToken) {
    return tokenService.refreshAccessToken(refreshToken);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByUserEmail(email);
  }

  public boolean existsByNickname(String nickname) {
    return userRepository.existsByUserNickname(nickname);
  }

  public boolean existsByBusinessRegistrationNumber(String businessNumber) {
    return userRepository.existsByRsDocumentBusinessId(businessNumber);
  }
}
