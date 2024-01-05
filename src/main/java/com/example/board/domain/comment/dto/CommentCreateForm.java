package com.example.board.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateForm {

//  @NotEmpty
  private String content;

  private Long tagId;

  private Long reviewId;
}
