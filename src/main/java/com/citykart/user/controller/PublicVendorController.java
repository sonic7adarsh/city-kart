package com.citykart.user.controller;

import com.citykart.product.dto.ProductDTO;
import com.citykart.product.service.IFace.ProductService;
import com.citykart.user.dto.UserDTO;
import com.citykart.vendor.service.IFace.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/user")
public class PublicVendorController {

    private final VendorService vendorService;

    private ProductService productService;

    public PublicVendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/vendors")
    public List<UserDTO> getVendorsByCityAndCategory(
            @RequestParam String city,
            @RequestParam String category) {

        if (category == null || category.isBlank()) {
            return vendorService.getActiveVendorsByCity(city);
        }

        return vendorService.getVendorsByCityAndCategory(city, category);
    }

    @GetMapping("/vendors/{vendorId}/products")
    public List<ProductDTO> getVendorProducts(@PathVariable Long vendorId) {
        return productService.getProductsByVendor(vendorId);
    }

}

