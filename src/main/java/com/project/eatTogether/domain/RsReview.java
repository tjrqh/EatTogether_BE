package com.project.eatTogether.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class RsReview {

    @Id //식당 리뷰 id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long rsReviewId;

    @ManyToOne // 유저 id
    @JoinColumn(name = "user_id" ,nullable = false)
    @JsonManagedReference
    public User user;

    @ManyToOne // 식당 id
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rsRestaurant;

    @OneToMany(mappedBy = "rsReview")
    @Column(nullable = false)
    private List<RsReviewComment> rsReviewComments;

    @Column(nullable = false)
    private String rsReviewContent;

    @Column
    public Byte rsReviewRate;

    @Column(nullable = false)
    public LocalDateTime rsReviewCreatedAt;

    @Column
    public LocalDateTime rsReviewUpdatedAt;

    @Column
    public LocalDateTime rsReviewDeletedAt;

    @Column
    public String rsReviewState;

    @Column
    public long rsReviewLike;

}
