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
    public Long rs_document_id;

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @Column
    public String rs_document_certifiacte;

    @Column
    public Long rs_document_business_id;

}
