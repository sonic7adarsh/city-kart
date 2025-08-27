package com.citykart.product.repository;

import com.citykart.product.entity.Product;
import com.citykart.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
    List<Product> findByCityAndCategory(String city, String category);

}
