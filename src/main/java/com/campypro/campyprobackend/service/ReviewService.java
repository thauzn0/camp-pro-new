package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review saveReview(Review review);
    Optional<Review> getReviewById(Long id);
    List<Review> getAllReviews();
    void deleteReview(Long id);
    List<Review> findByProductId(Long productId);
} 