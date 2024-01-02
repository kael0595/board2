package com.example.board.domain.review.service;

import com.example.board.domain.member.entity.Member;
import com.example.board.domain.review.dto.ReviewCreateForm;
import com.example.board.domain.review.entity.Review;
import com.example.board.domain.review.repository.ReviewRepository;
import com.example.board.standard.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    public Review findById(Long id) {
        Optional<Review> review = this.reviewRepository.findById(id);
        if (review.isPresent()) {
            return review.get();
        }
        throw new DataNotFoundException("review not found");
    }

    public void create(ReviewCreateForm reviewCreateForm, Member author) {

        Review review = Review.builder()
                .subject(reviewCreateForm.getSubject())
                .content(reviewCreateForm.getContent())
                .author(author)
                .createDate(LocalDateTime.now())
                .build();
        this.reviewRepository.save(review);
    }

    public void modifyValidate(Review review, Member author) {

        if (review == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 게시글입니다.");
        }

        if (!review.getAuthor().getUsername().equals(author.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
    }

    public void modify(Review review, ReviewCreateForm reviewCreateForm) {

        review = Review.builder()
                .subject(reviewCreateForm.getSubject())
                .content(reviewCreateForm.getContent())
                .modifyDate(LocalDateTime.now())
                .build();
        this.reviewRepository.save(review);
    }
}
