package com.project.eatTogether.presentation.adminPageController;


import com.project.eatTogether.application.dto.adminDto.ReviewDeclareStateSearchReadResponse;
import com.project.eatTogether.application.service.adminService.ReviewDeclareService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/review")
@RequiredArgsConstructor
public class ReviewDeclareController {

  private final ReviewDeclareService reviewDeclareService;

  @GetMapping("/declare")
  public List<ReviewDeclareStateSearchReadResponse> getReviewDeclare(@RequestParam String declare) {
    return reviewDeclareService.checkReviewDeclare(declare);
  }

  @PutMapping("declare/update/{id}")
  public ResponseEntity<String> updateReviewDeclare(@PathVariable Long id,
      @RequestParam String state) {
    reviewDeclareService.updateReviewDeclareState(id,state);
    return ResponseEntity.status(HttpStatus.OK).body("ok");
  }
}
