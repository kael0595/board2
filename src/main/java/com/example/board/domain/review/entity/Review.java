package com.example.board.domain.review.entity;

import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.member.entity.Member;
import com.example.board.standard.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

    private String subject;

    private String content;

    @ManyToOne
    private Member author;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToMany
    Set<Member> voter;
}
