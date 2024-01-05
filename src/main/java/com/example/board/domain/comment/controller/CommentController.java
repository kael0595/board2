package com.example.board.domain.comment.controller;

import com.example.board.domain.comment.dto.CommentCreateForm;
import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.comment.service.CommentService;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import com.example.board.domain.review.entity.Review;
import com.example.board.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final ReviewService reviewService;

    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/create/review/{id}")
    public String createComment(CommentCreateForm commentCreateForm) {

        return "comment/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/review/{id}")
    public String create(Model model,
                         @PathVariable("id") Long id,
                         Principal principal,
                         @Valid CommentCreateForm commentCreateForm,
                         BindingResult bindingResult) {

        Review review = this.reviewService.findById(id);

        Member author = this.memberService.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("review", review);
            return "review/detail";
        }

        this.commentService.createValidate(review);

        Comment tag = (commentCreateForm.getTagId() != null) ? this.commentService.getCommentById(commentCreateForm.getTagId()) : null;

        Comment comment = this.commentService.create(review, author, commentCreateForm, tag);

        return String.format("redirect:/review/%s#comment_%s", comment.getReview().getId(), comment.getId());

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/create/review/{reviewId}/{commentId}")
    public String createReplyComment(CommentCreateForm commentCreateForm) {

        return "comment/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/review/{reviewId}/{commentId}")
    public String createReply(@PathVariable("reviewId") Long reviewId,
                              @PathVariable("commentId") Long commentId,
                              @Valid CommentCreateForm commentCreateForm,
                              Principal principal,
                              BindingResult bindingResult,
                              Model model) {

        Review review = this.reviewService.findById(reviewId);

        Member author = this.memberService.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("review", review);
            return "review/detail";
        }

        Comment tag = this.commentService.getCommentById(commentId);

        this.commentService.createReplyValidate(review, tag);

        Comment reply = this.commentService.create(review, author, commentCreateForm, tag);

        tag.addChild(reply);

        return String.format("redirect:/review/%s#comment_%s", reply.getReview().getId(), reply.getId());
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/modify/{id}")
    public String modify(CommentCreateForm commentCreateForm,
                         @PathVariable("id") Long id,
                         Principal principal) {
        Comment comment = this.commentService.getCommentById(id);

        Member author = this.memberService.findByUsername(principal.getName());

        this.commentService.modifyValidate(comment, author);

        this.commentService.modify(comment, commentCreateForm);

        return "comment/create";

    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(Model model,
                         @PathVariable("id") Long id,
                         Principal principal,
                         @Valid CommentCreateForm commentCreateForm,
                         BindingResult bindingResult) {

        Comment comment = this.commentService.getCommentById(id);

        Member author = this.memberService.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) return "review/detail";

        this.commentService.modifyValidate(comment, author);

        this.commentService.modify(comment, commentCreateForm);

        return String.format("redirect:/review/%s#comment_%s", comment.getReview().getId(), comment.getId());

    }
}
