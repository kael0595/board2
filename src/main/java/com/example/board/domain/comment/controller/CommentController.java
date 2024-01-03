package com.example.board.domain.comment.controller;

import com.example.board.domain.comment.dto.CommentCreateForm;
import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.comment.service.CommentService;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import com.example.board.domain.review.entity.Review;
import com.example.board.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

  private final CommentService commentService;

  private final ReviewService reviewService;

  private final MemberService memberService;

  @PostMapping("/create/{id}")
  public String create(Model model,
                       @PathVariable("id") Long id,
                       Principal principal,
                       @Valid CommentCreateForm commentCreateForm,
                       BindingResult bindingResult) {

    Review review = this.reviewService.findById(id);

    Member author = this.memberService.findByUsername(principal.getName());

    if (bindingResult.hasErrors()) {
      model.addAttribute("review", review);
      return "review/detail";
    }

    this.commentService.createValidate(review);

    Comment tag = (commentCreateForm.getTagId() != null) ? this.commentService.getCommentById(commentCreateForm.getTagId()) : null;

    Comment comment = this.commentService.create(review, author, commentCreateForm, tag);

    return String.format("redirect:/review/%s#comment_%s",comment.getReview().getId(), comment.getId());

  }
}
