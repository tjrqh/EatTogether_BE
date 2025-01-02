package com.project.eatTogether.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.OwnerStatus;
import com.project.eatTogether.domain.enums.UserRole;
import com.project.eatTogether.domain.enums.UserStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false)
  private String userEmail;

  @Column(nullable = false)
  private String userName;

  @Column(nullable = false)
  private String userPw;

  @Column(nullable = false)
  private String userPhone;

//  @Column(nullable = false)
//  private String userState;

  @Column(nullable = false)
  private String userNickname;

  @Column(nullable = false)
  private LocalDate userBirthday;

  @Column(nullable = false)
  private String userGender;

  @Column
  private String userPhotoOrigin;

  @Column
  private String userPhotoPath;

  @Column
  private String userPhotoName;

//  @Column(nullable = false)
  private String userGrade;

  @Column
  private int userFollower;
  @Column
  private int userFollowing;

  @Column
  private String userAuth;

  // 점주 관련 필드
  @Column
  private String rsDocumentBusinessId;  // 사업자등록번호

  @Column
  private String businessLicensePath;         // 사업자등록증 경로

  @Column
  private String businessLicenseName;         // 사업자등록증 파일명

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole userRole;                    // 역할 (USER, OWNER)

  @Column(nullable = true)           // 점주 상태 (ACTIVE, INACTIVE, PENDING, SUSPENDED)
  @Enumerated(EnumType.STRING)
  private OwnerStatus ownerStatus;

  @Column(nullable = false)          // 일반 회원 상태
  @Enumerated(EnumType.STRING)
  private UserStatus userStatus = UserStatus.ACTIVE; // 기본값 설정;

  // 점주의 식당 목록
  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<RsRestaurant> restaurants = new ArrayList<>();

  //점주 승인 내역
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<OwnerApprovalHistory> approvalHistories = new ArrayList<>();


  @OneToMany(mappedBy = "user")
  private List<UserGroupMapping> userGroupMappings;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<RsReservation> rsReservation = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<RsReview> rsReviews = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<RsReviewComment> rsReviewComments;

  @OneToMany(mappedBy = "user")
  private List<Follow> follows;

  @OneToMany(mappedBy = "user")
  private List<Queue> queues;

  @OneToMany(mappedBy = "user")
  private List<Payment> payments;

  @OneToMany(mappedBy = "user")
  private List<QueueOrder> queueOrders;  // 수정된 부분

  @OneToMany(mappedBy = "user")
  private List<Bookmark> bookmarks;

  // 일반 회원 생성을 위한 정적 팩토리 메서드
  public static User createUser(
          String userEmail,
          String userPw,
          String userName,
          String userNickname,
          String userPhone,
          LocalDate userBirthday,
          String userGender
  ) {
    return User.builder()
            .userEmail(userEmail)
            .userPw(userPw)
            .userName(userName)
            .userNickname(userNickname)
            .userPhone(userPhone)
            .userBirthday(userBirthday)
            .userGender(userGender)
            .userRole(UserRole.USER)
            .userStatus(UserStatus.ACTIVE)
            .build();
  }

  // 점주 생성 정적 팩토리 메서드
  public static User createOwner(
          String userEmail,
          String userPw,
          String userName,
          String userPhone,
          String rsDocumentBusinessId,
          String businessLicensePath,
          String businessLicenseName
  ) {
    return User.builder()
            .userEmail(userEmail)
            .userPw(userPw)
            .userName(userName)
            .userPhone(userPhone)
            .rsDocumentBusinessId(rsDocumentBusinessId)
            .businessLicensePath(businessLicensePath)
            .businessLicenseName(businessLicenseName)
            .userRole(UserRole.OWNER)
            .userStatus(UserStatus.ACTIVE)
            .ownerStatus(OwnerStatus.PENDING)
            // 점주용 기본값 설정
            .userNickname(userName) // 이름을 닉네임 기본값으로
            .userBirthday(LocalDate.now())
            .userGender("NOT_SPECIFIED")
            .build();
  }

  // 점주 승인 메소드
  public void approveOwner() {
    if (this.userRole != UserRole.OWNER) {
      throw new IllegalStateException("점주 회원만 승인할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.APPROVED;
  }

  // 점주 승인 거절 메소드
  public void rejectOwner() {
    if (this.userRole != UserRole.OWNER) {
      throw new IllegalStateException("점주 회원만 거절할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.REJECTED;
  }

  // 점주 자격 정지 메소드
  public void suspendOwner() {
    if (this.userRole != UserRole.OWNER) {
      throw new IllegalStateException("점주 회원만 정지할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.SUSPENDED;
  }

  // 점주 탈퇴 메소드
  public void withdrawOwner() {
    if (this.userRole != UserRole.OWNER) {
      throw new IllegalStateException("점주 회원만 탈퇴할 수 있습니다.");
    }
    this.ownerStatus = OwnerStatus.WITHDRAWN;
  }

  // 회원 상태 변경 메소드
  public void updateUserStatus(UserStatus userStatus) {
    this.userStatus = userStatus;
  }

}
