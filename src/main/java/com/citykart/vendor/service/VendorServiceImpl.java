package com.citykart.vendor.service;

import com.citykart.exceptionHandler.ResourceNotFoundException;
import com.citykart.reviews.dto.ReviewDTO;
import com.citykart.subscription.util.SubscriptionPlanUtil;
import com.citykart.user.dto.UserDTO;
import com.citykart.user.entity.User;
import com.citykart.user.enums.Role;
import com.citykart.user.repository.UserRepository;
import com.citykart.vendor.service.IFace.VendorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final UserRepository repo;
    private final ModelMapper mapper;

    public VendorServiceImpl(final UserRepository repo, final ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public void registerVendor(final UserDTO dto) {
        User user = mapper.map(dto, User.class);
        user.setSubscriptionExpiry(
                Instant.now().toEpochMilli() + SubscriptionPlanUtil.getExpiryMillis(dto.getSubscriptionPlan())
        );
        user.setRole(Role.valueOf(dto.getName()));
        user.setActive(true);
        repo.save(user);
    }

    public List<UserDTO> getVendorsByCityAndCategory(String city, String category) {

        List<User> vendors =
                repo.findByRoleAndCityAndCategoryAndActiveTrue(Role.VENDOR, city, category);

        return vendors.stream()
                .map(user -> {
                    // build the DTO manually
                    UserDTO dto = UserDTO.builder()
                            .name(user.getName())
                            .phone(user.getPhone())
                            .email(user.getEmail())
                            .role(user.getRole().name())
                            .active(user.isActive())
                            .city(user.getCity())
                            .category(user.getCategory())
                            .address(user.getAddress())
                            .subscriptionPlan(user.getSubscriptionPlan())
                            .subscriptionExpiry(user.getSubscriptionExpiry())
                            .createdAt(user.getCreatedAt())
                            .build();

                    // map reviews (if any)
                    if (user.getMyReviews() != null && !user.getMyReviews().isEmpty()) {
                        List<ReviewDTO> reviews = user.getMyReviews().stream()
                                .map(r -> ReviewDTO.builder()
                                        .id(r.getId())
                                        .userId(r.getUser().getId())
                                        .userName(r.getUserName())
                                        .rating(r.getRating())
                                        .comment(r.getComment())
                                        .build())
                                .collect(Collectors.toList());

                        dto.setReviews(reviews);

                        double avg = reviews.stream()
                                .mapToInt(ReviewDTO::getRating)
                                .average()
                                .orElse(0.0);
                        dto.setAverageRating(avg);
                    } else {
                        dto.setReviews(Collections.emptyList());
                        dto.setAverageRating(0.0);
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }



    public UserDTO getByPhone(final String phone) {
        User user = repo.findByPhone(phone)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor with phone " + phone + " not found"));

        List<ReviewDTO> reviews = (user.getMyReviews() == null)
                ? Collections.emptyList()
                : user.getMyReviews().stream()
                .map(r -> ReviewDTO.builder()
                        .id(r.getId())
                        .userId(r.getUser().getId())
                        .userName(r.getUserName())
                        .rating(r.getRating())
                        .comment(r.getComment())
                        .build())
                .collect(Collectors.toList());

        double average = reviews.stream()
                .mapToInt(ReviewDTO::getRating)
                .average()
                .orElse(0.0);

        return UserDTO.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole().name())
                .active(user.isActive())
                .city(user.getCity())
                .category(user.getCategory())
                .address(user.getAddress())
                .subscriptionPlan(user.getSubscriptionPlan())
                .subscriptionExpiry(user.getSubscriptionExpiry())
                .createdAt(user.getCreatedAt())
                .reviews(reviews)
                .averageRating(average)
                .build();
    }


    public User renewSubscription(final String phone, final String plan) {
        Optional<User> user = repo.findByPhone(phone);
        if (Objects.isNull(user)) throw new ResourceNotFoundException("Vendor not found");

        User updateUser = user.get();
        long extension = SubscriptionPlanUtil.getExpiryMillis(plan);
        updateUser.setSubscriptionExpiry(Instant.now().toEpochMilli() + extension);
        updateUser.setActive(true);
        updateUser.setSubscriptionPlan(plan.toUpperCase());

        return repo.save(updateUser);
    }

    public List<UserDTO> getActiveVendorsByCity(final String city) {
        return repo.findByRoleAndCityAndActiveTrue(Role.VENDOR, city)
                .stream()
                .map(v -> mapper.map(v, UserDTO.class))
                .collect(Collectors.toList());
    }
}
