package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.adminDto.UserManagingReadResponse;
import com.project.eatTogether.application.service.userService.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("")
  public List<UserManagingReadResponse> getUserManagingSearch(@RequestParam String userNickName){
    return userService.getUserManagingSearch(userNickName);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> sanctionUser (@PathVariable Long id){
    id = 1L;
    return userService.sanctionUserService(id);
  }
}
