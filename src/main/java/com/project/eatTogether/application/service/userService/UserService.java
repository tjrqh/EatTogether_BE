package com.project.eatTogether.application.service.userService;

import com.project.eatTogether.application.dto.adminDto.UserManagingReadResponse;
import com.project.eatTogether.domain.User;
import com.project.eatTogether.domain.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  public ResponseEntity<String> userStateSanctionService(Long id) {
    Optional<User> user = userRepository.findById(id);
    User findByUser = user.orElseThrow(() ->
        new NoSuchElementException("Search Declare Not Found : " + id));
    int userState = Integer.parseInt(findByUser.getUserState());
    int sanction = 1 + userState;
    findByUser.setUserState(String.valueOf(sanction));
    userRepository.save(findByUser);

    return ResponseEntity.ok("Ok");
  }

  public List<UserManagingReadResponse> getUserManagingSearch(String userNickName) {
    try {
      if (userNickName.trim().isEmpty()) {
        return Collections.emptyList(); // 빈 리스트 반환
      }
      List<User> userList = userRepository.findByUserNickNameContaining(userNickName);

      return userList.stream().map(user -> UserManagingReadResponse
              .builder()
              .id(user.getUserId())
              .userName(user.getUserName())
              .userNickName(user.getUserNickName())
              .userState(user.getUserState())
              .deletedAt(String.valueOf(user.getUserDeletedAt()))
              .build())
          .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("FindByUser error : ", e);
      throw new RuntimeException(
          "Unexpected error occurred while processing review declare states : ", e);
    }
  }

  public ResponseEntity<String> sanctionUserService(Long id) {
    try {
      Optional<User> user = userRepository.findById(id);
      User findByUser = user.orElseThrow(() ->
          new NoSuchElementException("Search Declare Not Found : " + id));
      findByUser.setUserDeletedAt(LocalDateTime.now());
      userRepository.save(findByUser);
      return ResponseEntity.ok("Ok");
    } catch (Exception e) {
      log.error("FindByUser error : ", e);
      throw new RuntimeException(
          "Unexpected error occurred while processing review declare states : ", e);
    }
  }
}
