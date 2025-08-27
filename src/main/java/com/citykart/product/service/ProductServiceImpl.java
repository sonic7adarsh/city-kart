package com.citykart.product.service;

import com.citykart.auth.context.UserContextHolder;
import com.citykart.exceptionHandler.ResourceNotFoundException;
import com.citykart.exceptionHandler.UnauthorizedException;
import com.citykart.product.dto.ProductDTO;
import com.citykart.product.entity.Product;
import com.citykart.product.repository.ProductRepository;
import com.citykart.product.service.IFace.ProductService;
import com.citykart.reviews.dto.ReviewDTO;
import com.citykart.reviews.entity.Review;
import com.citykart.user.entity.User;
import com.citykart.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository repo, ModelMapper mapper, UserRepository userRepository) {
        this.repo = repo;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public Product addProduct(final ProductDTO dto) {
        User user = UserContextHolder.get(); // From JWT

        if (!user.isActive()) {
            throw new UnauthorizedException("Subscription expired. Please renew.");
        }

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .city(dto.getCity())
                .user(user)
                .build();

        // ✅ Manually map reviews (if any)
        if (dto.getReviews() != null && !dto.getReviews().isEmpty()) {
            List<Review> reviews = dto.getReviews().stream().map(r -> {
                Review review = Review.builder()
                        .userName(r.getUserName())
                        .user(user)
                        .rating(r.getRating())
                        .comment(r.getComment())
                        .createdAt(LocalDateTime.now())
                        .product(product) // ✅ important
                        .build();
                return review;
            }).collect(Collectors.toList());

            product.setReviews(reviews);
        }

        return repo.save(product);
    }


    public List<ProductDTO> getByCityAndCategory(final String city,
                                                 final String category) {

        return repo.findByCityAndCategory(city, category)
                .stream()
                .map(this::toDto)   // manual mapper
                .collect(Collectors.toList());
    }


    private ProductDTO toDto(Product p) {

        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setCategory(p.getCategory());
        dto.setCity(p.getCity());

        /* reviews → ReviewDTO */
        if (p.getReviews() != null && !p.getReviews().isEmpty()) {
            List<ReviewDTO> reviewDTOs = p.getReviews().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());

            dto.setReviews(reviewDTOs);

            double avg = reviewDTOs.stream()
                    .mapToInt(ReviewDTO::getRating)
                    .average()
                    .orElse(0.0);
            dto.setAverageRating(avg);

        } else {
            dto.setReviews(List.of());
            dto.setAverageRating(0.0);
        }

        return dto;
    }

    private ReviewDTO toDto(Review r) {
        return ReviewDTO.builder()
                .id(r.getId())
                .userName(r.getUserName())
                .userId(r.getUser() != null ? r.getUser().getId() : null)
                .rating(r.getRating())
                .comment(r.getComment())
                .build();
    }


    public List<ProductDTO> getMyProducts() {
        User user = UserContextHolder.get();
        if (!user.isActive()) {
            throw new UnauthorizedException("Subscription expired. Please renew.");
        }
        return repo.findByUser(user)
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public Product updateProduct(final Long id, final ProductDTO dto) {
        User user = UserContextHolder.get();
        if (!user.isActive()) {
            throw new UnauthorizedException("Subscription expired. Please renew.");
        }
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 🔐 Check vendor ownership
        if (!product.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: You can only update your own products");
        }

        // Update fields
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setCity(dto.getCity());

        return repo.save(product);
    }

    public void deleteProduct(final Long id) {
        User user = UserContextHolder.get();
        if (!user.isActive()) {
            throw new UnauthorizedException("Subscription expired. Please renew.");
        }
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: You can only delete your own products");
        }

        repo.delete(product);
    }


    public List<ProductDTO> getProductsByVendor(Long vendorId) {
        User user = userRepository.findByIdAndActiveTrue(vendorId);
        if(Objects.isNull(user)) {
            throw new ResourceNotFoundException("User not found");
        }
        return repo.findByUser(user)
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

}
