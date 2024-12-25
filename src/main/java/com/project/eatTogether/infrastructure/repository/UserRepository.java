package com.project.eatTogether.infrastructure.repository;

import com.project.eatTogether.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByUserNickNameContaining(String username);
}
