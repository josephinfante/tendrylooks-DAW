package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.OrderAllDto;
import com.tendrylooks.daw.main.dto.OrderDetailCreateDto;
import com.tendrylooks.daw.main.dto.OrderDetailDetailDto;
import com.tendrylooks.daw.main.dto.OrderDetailDto;
import com.tendrylooks.daw.main.entity.Order;
import com.tendrylooks.daw.main.entity.OrderDetail;
import com.tendrylooks.daw.main.repository.OrderDetailRepository;
import com.tendrylooks.daw.main.repository.OrderRepository;
import com.tendrylooks.daw.main.service.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void createOrderDetail(OrderDetailCreateDto orderDetailCreateDto) {
        orderDetailRepository.createOrderDetail(
                orderDetailCreateDto.codPed(),
                orderDetailCreateDto.codProd(),
                orderDetailCreateDto.canDetPed()
        );
    }

    @Override
    @Transactional
    public void updateOrderDetail(OrderDetailDetailDto orderDetailDetailDto) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(orderDetailDetailDto.codDetPed())
                .orElseThrow(() -> new EntityNotFoundException("OrderDetail not found for ID: " + orderDetailDetailDto.codDetPed()));

        if (orderDetailDetailDto.codPed() != null) {
            Order order = orderRepository.findById(orderDetailDetailDto.codPed())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found for ID: " + orderDetailDetailDto.codPed()));
            existingOrderDetail.setOrder(order);
        }
        if (orderDetailDetailDto.codProd() != null) {
            existingOrderDetail.setCodProd(orderDetailDetailDto.codProd());
        }
        if (orderDetailDetailDto.canDetPed() != null) {
            existingOrderDetail.setCanDetPed(orderDetailDetailDto.canDetPed());
        }

        orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public OrderDetailDetailDto findById(Integer codDetPed) {
        OrderDetail orderDetail = orderDetailRepository.findById(codDetPed)
                .orElseThrow(() -> new EntityNotFoundException("OrderDetail not found for ID: " + codDetPed));

        return new OrderDetailDetailDto(
                orderDetail.getCodDetPed(),
                orderDetail.getOrder().getCodPed(),
                orderDetail.getCodProd(),
                orderDetail.getCanDetPed(),
                orderDetail.getTotDetPed()
        );
    }

    @Override
    public Page<OrderAllDto> getAllOrderDetails(Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<OrderDetail> orderDetails = orderDetailRepository.spGetAllOrderDetails(limit, offset);

        List<OrderAllDto> orderDetailDtos = orderDetails.stream().map(this::convertToDto).collect(Collectors.toList());

        long totalOrderDetails = orderDetailRepository.count();

        return new PageImpl<>(orderDetailDtos, pageable, totalOrderDetails);
    }

    private OrderAllDto convertToDto(OrderDetail orderDetail) {
        return new OrderAllDto(
                orderDetail.getCodDetPed(),
                orderDetail.getOrder().getCodPed(),
                orderDetail.getCodProd(),
                orderDetail.getCanDetPed(),
                orderDetail.getTotDetPed()
        );
    }
}
