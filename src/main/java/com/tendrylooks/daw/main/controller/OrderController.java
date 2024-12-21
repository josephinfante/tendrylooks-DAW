package com.tendrylooks.daw.main.controller;

import com.tendrylooks.daw.main.dto.*;
import com.tendrylooks.daw.main.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        try {
            orderService.createOrder(orderCreateDto);
            return ResponseEntity.ok(new ApiResponseDto("Order created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error creating the order: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> updateOrder(@RequestBody OrderDetailDto orderDetailDto) {
        try {
            orderService.updateOrder(orderDetailDto);
            return ResponseEntity.ok(new ApiResponseDto("Order updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error updating the order: " + e.getMessage()));
        }
    }

    @GetMapping("/{codOrder}")
    public ResponseEntity findById(@PathVariable Integer codOrder) {
        try {
            OrderDetailDto orderDetail = orderService.findById(codOrder);
            return ResponseEntity.ok(orderDetail);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponseDto(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error fetching the order: " + e.getMessage()));
        }
    }

    @GetMapping
    public GetAllResponseDto<OrderDto> getAllOrders(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrderDto> orderPage = orderService.getAllOrders(pageable);
        return new GetAllResponseDto<>(
                orderPage.getContent(),
                orderPage.getSize(),
                orderPage.getNumber() + 1,
                orderPage.getTotalElements(),
                orderPage.getTotalPages()
        );
    }
}
