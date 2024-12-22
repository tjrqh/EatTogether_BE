package com.project.eatTogether.application.service.userService;

import com.project.eatTogether.domain.User;
import com.project.eatTogether.domain.repository.UserRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserRepository userRepository;

  public ResponseEntity<String> userStateSanctionService(Long id){
      Optional<User> user = userRepository.findById(id);
      User findByUser = user.orElseThrow(() ->
          new NoSuchElementException("Search Declare Not Found : " + id));
      int userState = Integer.parseInt(findByUser.getUserState());
      int sanction = 1+userState;
      findByUser.setUserState(String.valueOf(sanction));
      userRepository.save(findByUser);

      return ResponseEntity.ok("Ok");
  }


}
