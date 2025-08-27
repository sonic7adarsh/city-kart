package com.citykart.product.dto;

import com.citykart.reviews.dto.ReviewDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;

    private String name;
    private String description;
    private Double price;

    private String category;
    private String city;

    // ✅ Vendor info (optional fields from User)
//    private Long vendorId;
//    private String vendorName;
//    private String vendorPhone;

    // ✅ Ratings & Reviews
    private Double averageRating;
    private List<ReviewDTO> reviews;
}
