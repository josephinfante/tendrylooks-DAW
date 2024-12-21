package com.tendrylooks.daw.main.controller;

import com.tendrylooks.daw.main.dto.GetAllResponseDto;
import com.tendrylooks.daw.main.dto.ProductListingDto;
import com.tendrylooks.daw.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    ProductService productService;

    @GetMapping("/product-listing")
    public GetAllResponseDto<ProductListingDto> getAllProducts(@RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductListingDto> productPage = productService.getProductListing(pageable);
        return new GetAllResponseDto<>(
                productPage.getContent(),
                productPage.getSize(),
                productPage.getNumber() + 1,
                productPage.getTotalElements(),
                productPage.getTotalPages()
        );
    }
}
