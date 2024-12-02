package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long review_comment_id;

    @ManyToMany
    @Column(name = "rs_review_id" ,nullable = false)
    public RsReview rsReview;

    @ManyToMany
    @Column(name = "user_id" ,nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @Column
    public Date rs_comment_created_at;

    @Column
    public String rs_comment_content;

    @Column
    public String rs_comment_state;

    @Column
    public LocalDateTime rs_comment_created_at;

    @Column
    public LocalDateTime rs_comment_updated_at;

    @Column
    public LocalDateTime re_comment_deleted_at;

    @Column
    public Long rs_parent_comment_id;

    @Column
    public Long rs_comment_depth;

    //셀프조인된 부분은 어케 작성해야할지 모르겠름;;
    //Self Join 코파일럿이 해줌ㅋ
    @ManyToOne @JoinColumn(name = "rs_parent_comment_id")
    private RsReviewComment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<RsReviewComment> replies;

    @Column(nullable = false)
    private Integer rsCommentDepth;


}
