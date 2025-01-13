package com.project.eatTogether.domain.entity.differed;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.MemberRole;
import com.project.eatTogether.domain.enums.MemberStatus;
import com.project.eatTogether.domain.enums.OwnerStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@ToString(exclude = {"restaurants", "reservations", "reviews", "bookmarks"})
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String phone;

  @Column
  private String nickname;

  @Column
  private LocalDate birthday;

  @Column
  private String gender;

  @Column
  private String profilePhotoOrigin;

  @Column
  private String profilePhotoPath;

  @Column
  private String profilePhotoName;

  @Column
  private String grade;

  @Column
  private int follower = 0;

  @Column
  private int following = 0;

  @Column
  private String memberAuth;

  // 점주 관련 필드
  @Column
  private String businessRegistrationNumber;  // 사업자등록번호

  @Column
  private String businessLicensePath;         // 사업자등록증 경로

  @Column
  private String businessLicenseName;         // 사업자등록증 파일명

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private MemberRole role;                    // 역할 (USER, OWNER)

  @Column(nullable = true)           // 점주 상태 (ACTIVE, INACTIVE, PENDING, SUSPENDED)
  @Enumerated(EnumType.STRING)
  private OwnerStatus ownerStatus;

  @Column(nullable = false)          // 일반 회원 상태
  @Enumerated(EnumType.STRING)
  private MemberStatus memberStatus = MemberStatus.ACTIVE; // 기본값 설정;


  // 점주의 식당 목록
  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<Restaurant> restaurants = new ArrayList<>();

  // 예약 내역
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Reservation> reservations = new ArrayList<>();

  // 리뷰 내역
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

//  // 북마크한 식당 목록
//  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//  private List<RsBookmark> bookmarks = new ArrayList<>();


//  @OneToMany(mappedBy = "member")
//  private List<memberGroupMapping> memberGroupMappings;


//  @OneToMany(mappedBy = "member")
//  private List<RsReviewComment> rsReviewComments;
//
//  @OneToMany(mappedBy = "member")
//  private List<Follow> follows;
//
//  @OneToMany(mappedBy = "member")
//  private List<Queue> queues;

//  @OneToMany(mappedBy = "member")
//  private List<Payment> payments;

//  @OneToMany(mappedBy = "member")
//  private List<QueueOrder> queueOrders;
//


  // 일반 회원 생성을 위한 정적 팩토리 메서드
  public static Member createUser(
          String email,
          String password,
          String name,
          String nickname,
          String phone,
          LocalDate birthday,
          String gender
  ) {
    return Member.builder()
            .email(email)
            .password(password)
            .name(name)
            .nickname(nickname)
            .phone(phone)
            .birthday(birthday)
            .gender(gender)
            .role(MemberRole.USER)
            .memberStatus(MemberStatus.ACTIVE)
            .build();
  }

  // 점주 생성 정적 팩토리 메서드
  public static Member createOwner(
          String email,
          String password,
          String name,
          String phone,
          String businessRegistrationNumber,
          String businessLicensePath,
          String businessLicenseName
  ) {
    return Member.builder()
            .email(email)
            .password(password)
            .name(name)
            .phone(phone)
            .businessRegistrationNumber(businessRegistrationNumber)
            .businessLicensePath(businessLicensePath)
            .businessLicenseName(businessLicenseName)
            .role(MemberRole.OWNER)
            .memberStatus(MemberStatus.ACTIVE)
            .ownerStatus(OwnerStatus.PENDING)
            // 점주용 기본값 설정
            .nickname(name) // 이름을 닉네임 기본값으로
            .birthday(LocalDate.now())
            .gender("NOT_SPECIFIED")
            .build();
  }

  // 점주 승인 메소드
  public void approveOwner() {
    if (this.role != MemberRole.OWNER) {
      throw new IllegalStateException("점주 회원만 승인할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.APPROVED;
  }

  // 점주 승인 거절 메소드
  public void rejectOwner() {
    if (this.role != MemberRole.OWNER) {
      throw new IllegalStateException("점주 회원만 거절할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.REJECTED;
  }

  // 점주 자격 정지 메소드
  public void suspendOwner() {
    if (this.role != MemberRole.OWNER) {
      throw new IllegalStateException("점주 회원만 정지할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.SUSPENDED;
  }

  // 점주 탈퇴 메소드
  public void withdrawOwner() {
    if (this.role != MemberRole.OWNER) {
      throw new IllegalStateException("점주 회원만 탈퇴할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.WITHDRAWN;
  }

  // 회원 상태 변경 메소드
  public void updateMemberStatus(MemberStatus memberStatus) {
    this.memberStatus = memberStatus;
  }

}

