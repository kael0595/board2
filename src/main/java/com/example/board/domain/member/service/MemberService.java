package com.example.board.domain.member.service;

import com.example.board.domain.member.dto.MemberJoinForm;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signupValidate(BindingResult bindingResult, MemberJoinForm memberJoinForm) {
        if (!memberJoinForm.getPassword1().equals(memberJoinForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 서로 다릅니다.");
        }

        Member member = this.memberRepository.findByUsername(memberJoinForm.getUsername());

        if (member != null) {
            bindingResult.rejectValue("username", "usernameDuplicate", "중복된 아이디입니다.");
        }
    }

    public void signup(MemberJoinForm memberJoinForm) {

        Member member = Member.builder()
                .username(memberJoinForm.getUsername())
                .password(memberJoinForm.getPassword1())
                .email(memberJoinForm.getEmail())
                .createDate(LocalDateTime.now())
                .build();
        this.memberRepository.save(member);
    }
}
