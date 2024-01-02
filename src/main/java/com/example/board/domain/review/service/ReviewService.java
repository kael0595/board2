package com.example.board.domain.review.service;

import com.example.board.domain.review.entity.Review;
import com.example.board.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }
}
