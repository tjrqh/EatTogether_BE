package com.project.eatTogether.domain.entity;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Pictures  extends BaseEntity {
    @Id
    public String uuid;

    @Column
    public String path;

    @Column
    public String origin;

    @Column
    public String field;
}
