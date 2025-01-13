package com.project.eatTogether.domain.entity.differed;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.OwnerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OwnerApprovalHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OwnerStatus status;

    @Column(length = 500)
    private String reason;

    @Column
    private String actionBy;
}
