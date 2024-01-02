package com.example.board.domain.member.entity;

import com.example.board.standard.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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