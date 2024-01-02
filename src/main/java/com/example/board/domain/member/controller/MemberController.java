package com.example.board.domain.member.controller;

import com.example.board.domain.member.dto.MemberJoinForm;
import com.example.board.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberJoinForm memberJoinForm) {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberJoinForm memberJoinForm,
                         BindingResult bindingResult) {

        this.memberService.signupValidate(bindingResult, memberJoinForm);

        if (bindingResult.hasErrors()) return "member/signup";

        this.memberService.signup(memberJoinForm);

        return "redirect:/";
    }
}
