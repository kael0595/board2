package com.example.board.domain.comment.entity;

import com.example.board.domain.member.entity.Member;
import com.example.board.domain.review.entity.Review;
import com.example.board.standard.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

  private String content;

  @ManyToOne
  private Member author;

  @ManyToOne
  private Review review;

  @ManyToOne(fetch = FetchType.LAZY)
  private Comment tag;

  @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Comment> children;
}
