package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long groupId;

    @Column(nullable = false)
    public String groupName;

    @Column(nullable = false)
    public LocalDate groupCreatedAt;

    @Column
    public LocalDate groupUpdatedAt;

    @Column
    public LocalDate groupDeletedAt;

    @OneToMany(mappedBy = "group")
    private List<UserGroupMapping> userGroupMappings;

}
