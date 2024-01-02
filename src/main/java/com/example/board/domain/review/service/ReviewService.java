package com.example.board.domain.review.service;

import com.example.board.domain.member.entity.Member;
import com.example.board.domain.review.dto.ReviewCreateForm;
import com.example.board.domain.review.entity.Review;
import com.example.board.domain.review.repository.ReviewRepository;
import com.example.board.standard.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .createDate(LocalDateTime.now())
                .build();
        this.reviewRepository.save(review);
    }
}
