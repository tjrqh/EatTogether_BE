package com.project.eatTogether.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rsGroupId;

    @Column(nullable = false)
    public String rsGroupName;

    @Column(nullable = false)
    public LocalDateTime rsGroupCreatedAt;

    @Column
    public LocalDateTime rsGroupUpdatedAt;

    @Column
    public LocalDateTime rsGroupDeletedAt;

    @OneToMany(mappedBy = "rsGroup")
    private List<UserGroupMapping> userGroupMappings;

}
