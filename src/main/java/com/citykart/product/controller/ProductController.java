package com.citykart.product.controller;

import com.citykart.product.dto.ProductDTO;
import com.citykart.product.entity.Product;
import com.citykart.product.service.IFace.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody ProductDTO dto) {
        return service.addProduct(dto);
    }

    @GetMapping("/search")
    public List<ProductDTO> getByCityAndCategory(@RequestParam String city, @RequestParam String category) {
        return service.getByCityAndCategory(city, category);
    }

    @GetMapping("/my")
    public List<ProductDTO> getMyProducts() {
        return service.getMyProducts();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return "Product deleted successfully";
    }

}
