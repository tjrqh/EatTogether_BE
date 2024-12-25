package com.project.eatTogether.application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewReadResponse {

  private Long id;
  private String userName;
  private int rating;
  private String content;
  private String date;
  private List<MultipartFile> images;
  private String reply;
  private String visited;

}
