package com.citykart.reviews.service;


import com.citykart.product.entity.Product;
import com.citykart.product.repository.ProductRepository;
import com.citykart.reviews.dto.ReviewDTO;
import com.citykart.reviews.entity.Review;
import com.citykart.reviews.repository.ReviewRepository;
import com.citykart.reviews.service.IFace.ReviewService;
import com.citykart.user.entity.User;
import com.citykart.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    private final ModelMapper mapper;

    public ReviewServiceImpl(ReviewRepository reviewRepo, ProductRepository productRepo, UserRepository userRepo, ModelMapper mapper) {
        this.reviewRepo = reviewRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    public void addReview(Long productId, User reviewer, ReviewDTO dto) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = Review.builder()
                .user(reviewer)
                .product(product)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepo.save(review);
    }

    public List<ReviewDTO> getReviewsForProduct(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return reviewRepo.findByProduct(product).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public double getAverageRating(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return reviewRepo.findAverageRating(product) != null
                ? reviewRepo.findAverageRating(product)
                : 0.0;
    }

    private ReviewDTO mapToDTO(Review review) {
        return mapper.map(review, ReviewDTO.class);
    }
}
