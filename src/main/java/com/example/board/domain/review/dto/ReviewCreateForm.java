package com.example.board.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateForm {

    private String subject;

    private String content;
}
