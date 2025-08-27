package com.citykart.product.service.IFace;

import com.citykart.product.dto.ProductDTO;
import com.citykart.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct( ProductDTO dto);
    List<ProductDTO> getByCityAndCategory(String city, String category);
    List<ProductDTO> getMyProducts();
    Product updateProduct(final Long id, final ProductDTO dto);
    void deleteProduct(final Long id);

    List<ProductDTO> getProductsByVendor(Long vendorId);
}
