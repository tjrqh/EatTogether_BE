package com.project.eatTogether.domain;

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

    @OneToOne(mappedBy = "rsDocument")
    private RsRestaurant rsRestaurant;
}
