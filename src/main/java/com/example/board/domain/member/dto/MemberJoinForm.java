package com.example.board.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinForm {

    private String username;

    private String password1;

    private String password2;

    private String email;

}

