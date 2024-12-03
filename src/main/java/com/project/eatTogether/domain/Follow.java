package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long follow_id;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    public User user;

    @Column
    public String following_id;

}
