package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String uuid;

    @Column
    public String path;

    @Column
    public String origin;

    @Column
    public String field;
}
