package com.example.board.domain.review.controller;

import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import com.example.board.domain.review.dto.ReviewCreateForm;
import com.example.board.domain.review.entity.Review;
import com.example.board.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model) {

        List<Review> reviewList = this.reviewService.findAll();

        model.addAttribute("reviewList", reviewList);

        return "review/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         Model model) {

        Review review = this.reviewService.findById(id);

        model.addAttribute("review", review);

        return "review/detail";
    }

    @GetMapping("/create")
    public String create(ReviewCreateForm reviewCreateForm) {
        return "review/create";
    }

    @PostMapping("/create")
    public String create(@Valid ReviewCreateForm reviewCreateForm,
                         BindingResult bindingResult,
                         Principal principal) {
        if (bindingResult.hasErrors()) return "review/create";

        Member author = this.memberService.findByUsername(principal.getName());

        reviewService.create(reviewCreateForm, author);

        return "redirect:/review/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(ReviewCreateForm reviewCreateForm,
                         @PathVariable("id") Long id,
                         Principal principal) {

        Review review = reviewService.findById(id);

        Member author = memberService.findByUsername(principal.getName());

        reviewService.modifyValidate(review, author);

        reviewService.modify(review, reviewCreateForm);

        return "review/create";
    }

    @PostMapping("/modify/{id}")
    public String modify(@Valid ReviewCreateForm reviewCreateForm,
                         @PathVariable("id") Long id,
                         BindingResult bindingResult,
                         Principal principal) {

        if (bindingResult.hasErrors()) return "review/create";

        Review review = reviewService.findById(id);

        Member author = memberService.findByUsername(principal.getName());

        reviewService.modifyValidate(review, author);

        reviewService.modify(review, reviewCreateForm);

        return String.format("redirect:/review/%s", id);
    }
}
