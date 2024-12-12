package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.ProductCreateDto;
import com.tendrylooks.daw.main.dto.ProductDetailDto;
import com.tendrylooks.daw.main.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    void createProduct(ProductCreateDto productCreateDto);
    void updateProduct(ProductDetailDto productDetailDto);
    ProductDetailDto findById(Integer codProd);
    Page<ProductDto> getAllProducts(Pageable pageable);
}
