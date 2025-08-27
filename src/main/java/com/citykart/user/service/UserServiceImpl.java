package com.citykart.user.service;

import com.citykart.reviews.entity.Review;
import com.citykart.subscription.util.SubscriptionPlanUtil;
import com.citykart.user.dto.UserDTO;
import com.citykart.user.entity.User;
import com.citykart.user.enums.Role;
import com.citykart.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public void registerIfNotExists(final UserDTO dto) {
        try {
            User user = mapper.map(dto, User.class);

            user.setSubscriptionExpiry(
                    Instant.now().toEpochMilli() + SubscriptionPlanUtil.getExpiryMillis(dto.getSubscriptionPlan())
            );
            user.setRole(Role.valueOf(dto.getRole()));
            user.setActive(true);

            // ⭐ Manually map reviews if any
            if (dto.getReviews() != null && !dto.getReviews().isEmpty()) {
                List<Review> reviewList = dto.getReviews().stream()
                        .map(r -> Review.builder()
                                .userName(r.getUserName())
                                .rating(r.getRating())
                                .createdAt(LocalDateTime.now())
                                .comment(r.getComment())
                                .user(user)  // ⭐ link user
                                .build())
                        .collect(Collectors.toList());

                user.setMyReviews(reviewList);
            }

            repo.save(user);

        } catch (Exception e) {
            log.error("Something went wrong while registering user", e);
        }
    }


    public User getByPhone(final String phone) {
        return repo.findByPhone(phone).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
