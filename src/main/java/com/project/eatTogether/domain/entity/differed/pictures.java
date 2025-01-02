package com.project.eatTogether.domain.entity.differed;

import com.project.eatTogether.domain.enums.ReferenceType;
import jakarta.persistence.*;

public class pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_id")
    private Long referenceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "reference_type")
    private ReferenceType referenceType;

    @Column(name = "origin")
    private String origin;

    @Column(name="path")
    private String path;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "field")
    private String field;

}

