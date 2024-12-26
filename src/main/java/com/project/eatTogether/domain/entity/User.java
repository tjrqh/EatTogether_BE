package com.project.eatTogether.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

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

  @Column(nullable = false)
  private String userState;

  @Column(nullable = false)
  private String userNickName;

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

  @Column(nullable = false)
  private String userGrade;

  @Column
  private int userFollower;
  @Column
  private int userFollowing;

  @Column
  private String userAuth;

  @Column(nullable = false)
  private LocalDateTime userCreatedAt;

  @Column
  private LocalDateTime userUpdatedAt;

  @Column
  private LocalDateTime userDeletedAt;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @JsonBackReference
  private UserRole userRole;

  @OneToMany(mappedBy = "user")
  private List<UserGroupMapping> userGroupMappings;

  @OneToMany(mappedBy = "user")
  private List<RsReservation> rsReservation;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<RsReview> rsReviews;

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

}
