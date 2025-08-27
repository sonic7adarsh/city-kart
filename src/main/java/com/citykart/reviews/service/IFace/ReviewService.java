package com.citykart.reviews.service.IFace;

import com.citykart.reviews.dto.ReviewDTO;
import com.citykart.user.entity.User;

import java.util.List;

public interface ReviewService {
    public void addReview(Long productId, User user, ReviewDTO dto);

    public List<ReviewDTO> getReviewsForProduct(Long productId);

    public double getAverageRating(Long productId);
}
