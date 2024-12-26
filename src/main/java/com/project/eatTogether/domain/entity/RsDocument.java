package com.project.eatTogether.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsDocumentId;

    @Column
    private String rsDocumentCertificate;

    @Column
    private String rsDocumentBusinessId;

    // RsRestaurant와의 관계
    @OneToOne(mappedBy = "rsDocument")  // RsRestaurant 클래스의 rsDocument 필드를 참조
    private RsRestaurant rsRestaurant;
}
