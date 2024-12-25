package com.project.eatTogether.presentation.adminPageController;


import com.project.eatTogether.application.dto.adminDto.ReviewDeclareReadResponse;
import com.project.eatTogether.application.service.adminService.ReviewDeclareService;
import com.project.eatTogether.application.service.userService.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/review/declare")
@RequiredArgsConstructor
public class ReviewDeclareController {

  private final ReviewDeclareService reviewDeclareService;

  @GetMapping("")
  public List<ReviewDeclareReadResponse> getReviewDeclare() {
  Long id = 1L;
    return reviewDeclareService.checkReviewDeclare(id);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Map<String, String>> updateReviewDeclare(@PathVariable Long id,
      @RequestParam String state) {
    reviewDeclareService.updateReviewDeclareState(id, state);
    Map<String, String> response = new HashMap<>();
    response.put("message", "ok");
    return ResponseEntity.ok(response);
  }

  @PutMapping("delete/{id}")
  public ResponseEntity<Map<String, String>> deleteReviewDeclare(@PathVariable Long id, @RequestParam String state) {
    reviewDeclareService.updateReviewDeclareState(id,state);

    Map<String, String> response = new HashMap<>();
    response.put("message", "ok");
    return ResponseEntity.ok(response);
  }
}
