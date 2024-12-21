package com.tendrylooks.daw.main.controller;


import com.tendrylooks.daw.main.dto.*;
import com.tendrylooks.daw.main.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createCart(@RequestBody CartCreateDto cartCreateDto) {
        try {
            cartService.createCart(cartCreateDto);
            return ResponseEntity.ok(new ApiResponseDto("Cart created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error creating cart: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> updateCart(@RequestBody CartForDetailDto cartForDetailDto) {
        try {
            cartService.updateCart(cartForDetailDto);
            return ResponseEntity.ok(new ApiResponseDto("Cart updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error updating cart: " + e.getMessage()));
        }
    }

    @GetMapping("/{codCarr}")
    public ResponseEntity findById(@PathVariable Integer codCarr) {
        try {
            CartForDetailDto cartDetail = cartService.findById(codCarr);
            return ResponseEntity.ok(cartDetail);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponseDto(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error getting the cart: " + e.getMessage()));
        }
    }

    @GetMapping
    public GetAllResponseDto<CartDto> getAllCarts(@RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CartDto> cartPage = cartService.getAllCarts(pageable);
        return new GetAllResponseDto<>(
                cartPage.getContent(),
                cartPage.getSize(),
                cartPage.getNumber() + 1,
                cartPage.getTotalElements(),
                cartPage.getTotalPages()
        );
    }
}