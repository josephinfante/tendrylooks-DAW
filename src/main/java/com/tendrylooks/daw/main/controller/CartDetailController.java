package com.tendrylooks.daw.main.controller;

import com.tendrylooks.daw.main.dto.*;
import com.tendrylooks.daw.main.service.CartDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-detail")
public class CartDetailController {

    @Autowired
    CartDetailService cartDetailService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createCartDetail(@RequestBody CartDetailCreateDto cartDetailCreateDto) {
        try {
            cartDetailService.createCartDetail(cartDetailCreateDto);
            return ResponseEntity.ok(new ApiResponseDto("CartDetail created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error creating CartDetail: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> updateCartDetail(@RequestBody CartDetailDetailDto cartDetailDetailDto) {
        try {
            cartDetailService.updateCartDetail(cartDetailDetailDto);
            return ResponseEntity.ok(new ApiResponseDto("CartDetail updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error updating CartDetail: " + e.getMessage()));
        }
    }

    @GetMapping("/{codDetCarr}")
    public ResponseEntity findById(@PathVariable Integer codDetCarr) {
        try {
            CartDetailDetailDto cartDetailDetail = cartDetailService.findById(codDetCarr);
            return ResponseEntity.ok(cartDetailDetail);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponseDto(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error getting the CartDetail: " + e.getMessage()));
        }
    }

    @GetMapping
    public GetAllResponseDto<CartDetailDto> getAllCartDetails(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CartDetailDto> cartDetailPage = cartDetailService.getAllCartDetails(pageable);
        return new GetAllResponseDto<>(
                cartDetailPage.getContent(),
                cartDetailPage.getSize(),
                cartDetailPage.getNumber() + 1,
                cartDetailPage.getTotalElements(),
                cartDetailPage.getTotalPages()
        );
    }
}
