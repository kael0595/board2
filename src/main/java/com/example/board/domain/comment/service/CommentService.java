package com.example.board.domain.comment.service;

import com.example.board.domain.comment.dto.CommentCreateForm;
import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.comment.repository.CommentRepository;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.review.entity.Review;
import com.example.board.standard.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  public void createValidate(Review review) {
    if (review == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 리뷰입니다.");
    }
  }

  public Comment create(Review review, Member author, CommentCreateForm commentCreateForm, Comment tag) {
    Comment comment = Comment.builder()
        .review(review)
        .content(commentCreateForm.getContent())
        .author(author)
        .tag(tag != null ? tag : null)
        .createDate(LocalDateTime.now())
        .build();
    this.commentRepository.save(comment);
    return comment;
  }

  public Comment getCommentById(Long id) {
    Optional<Comment> comment = this.commentRepository.findById(id);
    if(comment.isPresent()) {
      return comment.get();
    } throw new DataNotFoundException("comment not found");
  }
}
