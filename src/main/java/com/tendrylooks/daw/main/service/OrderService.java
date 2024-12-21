package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.OrderCreateDto;
import com.tendrylooks.daw.main.dto.OrderDetailDto;
import com.tendrylooks.daw.main.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    void createOrder(OrderCreateDto orderCreateDto);
    void updateOrder(OrderDetailDto orderDetailDto);
    OrderDetailDto findById(Integer codPed);
    Page<OrderDto> getAllOrders(Pageable pageable);
}
