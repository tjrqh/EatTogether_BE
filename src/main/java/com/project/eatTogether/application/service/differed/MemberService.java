package com.project.eatTogether.application.service.differed;

import com.project.eatTogether.application.dto.differed.login.LoginRequestDto;
import com.project.eatTogether.application.dto.differed.login.OwnerLoginResponseDto;
import com.project.eatTogether.application.dto.differed.login.UserLoginResponseDto;
import com.project.eatTogether.application.dto.differed.member.OwnerSignUpDto;
import com.project.eatTogether.application.dto.differed.member.SignUpResponseDto;
import com.project.eatTogether.application.dto.differed.member.UserSignUpDto;
import com.project.eatTogether.domain.Address;
import com.project.eatTogether.domain.entity.differed.CuisineCategories;
import com.project.eatTogether.domain.entity.differed.Document;
import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.domain.entity.differed.Restaurant;
import com.project.eatTogether.domain.enums.MemberRole;
import com.project.eatTogether.domain.enums.MemberStatus;
import com.project.eatTogether.domain.enums.OwnerStatus;
import com.project.eatTogether.infrastructure.differed.CuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.differed.MemberRepository;
import com.project.eatTogether.infrastructure.differed.RestaurantRepository;
import com.project.eatTogether.infrastructure.util.JWTUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.eatTogether.domain.enums.RestaurantStatus.CLOSED;
import static com.project.eatTogether.domain.enums.RestaurantStatus.OPEN;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final CuisineCategoriesRepository cuisineCategoriesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final EntityValidationService entityValidationService;
    private final TokenService tokenService;
    private final FileService fileService;

    /*일반 사용자 회원가입*/
    @Transactional
    public SignUpResponseDto signUpUser(UserSignUpDto requestDto) {
        //이메일 중복확인
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        //닉네임 중복확인
        if (memberRepository.existsByNickname(requestDto.getNickname())) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.createUser(
                requestDto.getEmail(),
                encodedPassword,
                requestDto.getName(),
                requestDto.getNickname(),
                requestDto.getPhone(),
                requestDto.getBirthday(),
                requestDto.getGender()
        );


        Member savedMember = memberRepository.save(member);
        return SignUpResponseDto.from(savedMember);
    }

    /*점주 회원가입*/
    @Transactional
    public SignUpResponseDto signUpOwner(OwnerSignUpDto dto, String uploadFileName) {
                                         // 이메일 중복 확인
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        // 사업자등록번호 중복 확인
        if (memberRepository.existsByBusinessRegistrationNumber(dto.getBusinessRegistrationNumber())) {
            throw new IllegalArgumentException("이미 등록된 사업자등록번호입니다.");
        }

        // 사업자등록증 파일 저장
        MultipartFile businessLicense = dto.getBusinessLicense();
        String savedFileName = fileService.saveFile(businessLicense, "business-license");
        String filePath = fileService.getFilePath(savedFileName);

        // Member 생성 및 저장
        Member owner = Member.createOwner(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getOwnerName(),
                dto.getOwnerPhone(),
                dto.getBusinessRegistrationNumber(),
                filePath,
                savedFileName
        );
        Member savedOwner = memberRepository.save(owner);

        // Document 생성
        Document document = Document.builder()
                .businessRegistrationNumber(dto.getBusinessRegistrationNumber())
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
        Restaurant restaurant = Restaurant.createRestaurant(
                dto.getRestaurantName(),
                dto.getRestaurantPhone(),
                savedOwner,
                address,
                document,
                null  // CuisineCategories는 별도로 설정
        );

        // 카테고리 조회 및 설정
        CuisineCategories cuisineCategory = cuisineCategoriesRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("해당 음식 카테고리를 찾을 수 없습니다"));
        restaurant.setCuisineCategory(cuisineCategory);

        restaurantRepository.save(restaurant);

        return SignUpResponseDto.from(savedOwner);
    }

    /*모든 회원 조회*/
    public List<SignUpResponseDto> findAllMember() {
        List<Member> result = memberRepository.findAll();
        return result.stream()
                .map(SignUpResponseDto::from)
                .collect(Collectors.toList());
    }

    /*이메일로 회원 조회*/
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));
    }

    /**로그인*/
    @Transactional(readOnly = true)
    public Object login(LoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = tokenService.generateAccessToken(member.getEmail());
        String refreshToken = tokenService.generateRefreshToken(member.getEmail());

        // 점주 회원인 경우
        if (member.getRole() == MemberRole.OWNER) {
            if (member.getOwnerStatus() != OwnerStatus.APPROVED) {
                throw new IllegalStateException("아직 관리자 승인이 완료되지 않았습니다. " +
                        member.getOwnerStatus().getMessage());
            }

            Restaurant restaurant = restaurantRepository.findByOwner(member)
                    .orElseThrow(() -> new IllegalStateException("식당 정보를 찾을 수 없습니다."));

            return OwnerLoginResponseDto.builder()
                    .accessToken("Bearer " + accessToken)
                    .refreshToken("Bearer " + refreshToken)
                    .email(member.getEmail())
                    .name(member.getName())
                    .role(member.getRole().getKey())
                    .businessRegistrationNumber(member.getBusinessRegistrationNumber())
                    .restaurantName(restaurant.getName())
                    .ownerStatus(member.getOwnerStatus())
                    .build();
        }

        // 일반 회원인 경우
        if (member.getMemberStatus() != MemberStatus.ACTIVE) {
            throw new IllegalStateException("활성화되지 않은 계정입니다.");
        }

        return UserLoginResponseDto.builder()
                .accessToken("Bearer " + accessToken)
                .refreshToken("Bearer " + refreshToken)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .name(member.getName())
                .role(member.getRole().getKey())
                .build();
    }

    @Transactional(readOnly = true)
    public List<Member> getPendingOwners() {
        return memberRepository.findByRoleAndOwnerStatus(MemberRole.OWNER, OwnerStatus.PENDING);
    }

    @Transactional
    public void approveOwner(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (member.getRole() != MemberRole.OWNER) {
            throw new IllegalArgumentException("점주 회원이 아닙니다.");
        }

        // 점주 승인
        member.approveOwner();

        // 해당 점주의 식당 찾기
        Restaurant restaurant = restaurantRepository.findByOwner(member)
                .orElseThrow(() -> new IllegalArgumentException("식당 정보를 찾을 수 없습니다."));

        // 식당 상태 변경
        restaurant.setStatus(OPEN);
    }

    @Transactional
    public void rejectOwner(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (member.getRole() != MemberRole.OWNER) {
            throw new IllegalArgumentException("점주 회원이 아닙니다.");
        }

        // 점주 거절
        member.rejectOwner();

        // 해당 점주의 식당 찾기
        Restaurant restaurant = restaurantRepository.findByOwner(member)
                .orElseThrow(() -> new IllegalArgumentException("식당 정보를 찾을 수 없습니다."));

        // 식당 상태 변경
        restaurant.setStatus(CLOSED);
    }

    @Transactional
    public void suspendOwner(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (member.getRole() != MemberRole.OWNER) {
            throw new IllegalArgumentException("점주 회원이 아닙니다.");
        }

        // 점주 자격 정지
        member.suspendOwner();

        // 해당 점주의 식당 찾기
        Restaurant restaurant = restaurantRepository.findByOwner(member)
                .orElseThrow(() -> new IllegalArgumentException("식당 정보를 찾을 수 없습니다."));

        // 식당 상태 변경
        restaurant.setStatus(CLOSED);
    }

    @Transactional(readOnly = true)
    public List<Restaurant> getRestaurantsByMemberId(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (member.getRole() != MemberRole.OWNER) {
            throw new IllegalArgumentException("식당 점주만 조회할 수 있습니다.");
        }

        return member.getRestaurants();
    }

    /** Access Token 재발급 */
    @Transactional(readOnly = true)
    public String refreshAccessToken(String refreshToken) {
        return tokenService.refreshAccessToken(refreshToken);
    }

    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean existsByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public boolean existsByBusinessRegistrationNumber(String businessNumber) {
        return memberRepository.existsByBusinessRegistrationNumber(businessNumber);
    }
}
