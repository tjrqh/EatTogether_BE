package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByUserNickNameContaining(String username);
}
