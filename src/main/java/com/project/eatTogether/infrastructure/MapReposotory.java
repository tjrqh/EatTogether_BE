package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapReposotory extends JpaRepository<RsCoordinates, Long> {
}
