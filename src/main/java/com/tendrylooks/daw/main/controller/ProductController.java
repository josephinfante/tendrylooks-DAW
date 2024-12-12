package com.tendrylooks.daw.main.controller;

import com.tendrylooks.daw.main.dto.*;
import com.tendrylooks.daw.main.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        try {
            productService.createProduct(productCreateDto);
            return ResponseEntity.ok(new ApiResponseDto("Product created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error creating product: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> updateProduct(@RequestBody ProductDetailDto productDetailDto) {
        try {
            productService.updateProduct(productDetailDto);
            return ResponseEntity.ok(new ApiResponseDto("Product updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error updating product: " + e.getMessage()));
        }
    }

    @GetMapping("/{codProd}")
    public ResponseEntity findById(@PathVariable Integer codProd) {
        try {
            ProductDetailDto productDetail = productService.findById(codProd);
            return ResponseEntity.ok(productDetail);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponseDto(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error getting the product: " + e.getMessage()));
        }
    }

    @GetMapping
    public GetAllResponseDto<ProductDto> getAllProducts(@RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductDto> productPage = productService.getAllProducts(pageable);
        return new GetAllResponseDto<>(
                productPage.getContent(),
                productPage.getSize(),
                productPage.getNumber() + 1,
                productPage.getTotalElements(),
                productPage.getTotalPages()
        );
    }
}
