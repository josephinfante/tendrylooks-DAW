package com.tendrylooks.daw.main.controller;

import com.tendrylooks.daw.main.dto.*;
import com.tendrylooks.daw.main.service.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/order-detail")
@Validated
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createOrderDetail(@RequestBody OrderDetailCreateDto orderDetailCreateDto) {
        try {
            orderDetailService.createOrderDetail(orderDetailCreateDto);
            return ResponseEntity.ok(new ApiResponseDto("OrderDetail created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error creating OrderDetail: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> updateOrderDetail(@RequestBody OrderDetailDetailDto orderDetailDetailDto) {
        try {
            orderDetailService.updateOrderDetail(orderDetailDetailDto);
            return ResponseEntity.ok(new ApiResponseDto("OrderDetail updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error updating OrderDetail: " + e.getMessage()));
        }
    }

    @GetMapping("/{codDetPed}")
    public ResponseEntity<?> findById(@PathVariable Integer codDetPed) {
        try {
            OrderDetailDetailDto orderDetail = orderDetailService.findById(codDetPed);
            return ResponseEntity.ok(orderDetail);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponseDto(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error getting the OrderDetail: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<GetAllResponseDto<OrderAllDto>> getAllOrderDetails(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<OrderAllDto> orderDetailPage = orderDetailService.getAllOrderDetails(pageable);
            GetAllResponseDto<OrderAllDto> response = new GetAllResponseDto<>(
                    orderDetailPage.getContent(),
                    orderDetailPage.getSize(),
                    orderDetailPage.getNumber() + 1,
                    orderDetailPage.getTotalElements(),
                    orderDetailPage.getTotalPages()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new GetAllResponseDto<>(null, size, page, 0, 0));
        }
    }
}