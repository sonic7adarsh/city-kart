package com.citykart.user.repository;


import com.citykart.user.entity.User;
import com.citykart.user.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);

    List<User> findByActiveTrueAndSubscriptionExpiryBetween(long l, long l1);

    List<User> findByActiveTrueAndSubscriptionExpiryLessThan(long now);

    List<User> findByCityAndCategoryAndActiveTrue(String city, String category);

    List<User> findByRoleAndCityAndCategoryAndActiveTrue(Role role, String city, String category);

    List<User> findByRole(Role role);

    List<User> findByRoleAndCityAndActiveTrue(Role role, String city);

    User findByIdAndActiveTrue(Long vendorId);
}
