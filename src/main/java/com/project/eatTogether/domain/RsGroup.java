package com.project.eatTogether.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long RsGroupId;

    @Column(nullable = false)
    public String RsGroupName;

    @Column(nullable = false)
    public LocalDateTime RsGroupCreatedAt;

    @Column
    public LocalDateTime RsGroupUpdatedAt;

    @Column
    public LocalDateTime RsGroupDeletedAt;

    @OneToMany(mappedBy = "rsGroup")
    private List<UserGroupMapping> userRsGroupMappings;

}
