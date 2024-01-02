package com.example.board.domain.review.controller;

import com.example.board.domain.review.entity.Review;
import com.example.board.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

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
}
