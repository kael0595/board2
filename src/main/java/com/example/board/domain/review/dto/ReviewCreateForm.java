package com.example.board.domain.review.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateForm {

    @NotEmpty
    private String subject;

    @NotEmpty
    private String content;
}
