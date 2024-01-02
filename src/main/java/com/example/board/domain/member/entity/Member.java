package com.example.board.domain.member.entity;

import com.example.board.domain.review.entity.Review;
import com.example.board.standard.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

}