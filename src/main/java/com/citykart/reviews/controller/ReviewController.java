package com.citykart.reviews.controller;

import com.citykart.auth.context.UserContextHolder;
import com.citykart.reviews.dto.ReviewDTO;
import com.citykart.reviews.service.IFace.ReviewService;
import com.citykart.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<String> submitReview(@PathVariable Long productId,
                                               @RequestBody ReviewDTO dto) {
        User user = UserContextHolder.get(); // JWT se current user
        reviewService.addReview(productId, user, dto);
        return ResponseEntity.ok("Review submitted successfully");
    }

    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getProductReviews(@PathVariable Long productId) {
        return reviewService.getReviewsForProduct(productId);
    }

    @GetMapping("/product/{productId}/rating")
    public double getProductRating(@PathVariable Long productId) {
        return reviewService.getAverageRating(productId);
    }
}
