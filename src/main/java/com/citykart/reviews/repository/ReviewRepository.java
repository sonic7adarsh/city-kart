package com.citykart.reviews.repository;

import com.citykart.reviews.entity.Review;
import com.citykart.product.entity.Product;
import com.citykart.user.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product = :product")
    Double findAverageRating(@Param("product") Product product);

    List<Review> findByUser(User user);
}
