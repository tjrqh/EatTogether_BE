package com.project.eatTogether.infrastructure.differed;

import com.project.eatTogether.domain.entity.differed.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapReposotory extends JpaRepository<Coordinates, Long> {
}
