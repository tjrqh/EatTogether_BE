package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private Byte user_role_id;

    @Column(nullable = false)
    private String user_email;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String user_pw;

    @Column(nullable = false)
    private String user_phone;

    @Column(nullable = false)
    private String user_state;

    @Column(nullable = false)
    private String user_nickname;

    @Column(nullable = false)
    private Date user_birth;

    @Column(nullable = false)
    private String user_gender;

    @Column
    private String user_photo_origin;

    @Column
    private String user_photo_path;

    @Column
    private String user_photo_name;

    @Column(nullable = false)
    private String user_grade;

    @Column
    private Long user_follower;

    @Column
    private Long user_following;

    @Column
    private String user_auth;

    @Column(nullable = false)
    private Date user_created_at;

    @Column
    private Date user_updated_at;

    @Column
    private Date user_deleted_at;
}
