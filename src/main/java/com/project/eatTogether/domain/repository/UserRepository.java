package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
