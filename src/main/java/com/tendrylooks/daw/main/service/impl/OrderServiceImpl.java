package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.OrderCreateDto;
import com.tendrylooks.daw.main.dto.OrderDetailDto;
import com.tendrylooks.daw.main.dto.OrderDto;
import com.tendrylooks.daw.main.entity.Order;
import com.tendrylooks.daw.main.repository.OrderRepository;
import com.tendrylooks.daw.main.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(OrderCreateDto orderCreateDto) {
        orderRepository.createOrder(
                orderCreateDto.codUsu(),
                orderCreateDto.totPed(),
                orderCreateDto.contPed(),
                orderCreateDto.telPed(),
                orderCreateDto.dirPed()
        );
    }

    @Override
    public void updateOrder(OrderDetailDto orderDetailDto) {
        Order existingOrder = orderRepository.findById(orderDetailDto.codPed())
                .orElseThrow(() -> new EntityNotFoundException("Order not found for ID: " + orderDetailDto.codPed()));

        if (orderDetailDto.codUsu() != null) {
            existingOrder.getUser().setCodUsu(orderDetailDto.codUsu());
        }

        if (orderDetailDto.totPed() != null) {
            existingOrder.setTotPed(orderDetailDto.totPed());
        }
        if (orderDetailDto.contPed() != null) {
            existingOrder.setContPed(orderDetailDto.contPed());
        }
        if (orderDetailDto.telPed() != null) {
            existingOrder.setTelPed(orderDetailDto.telPed());
        }
        if (orderDetailDto.dirPed() != null) {
            existingOrder.setDirPed(orderDetailDto.dirPed());
        }
        if (orderDetailDto.estPed() != null) {
            existingOrder.setEstPed(orderDetailDto.estPed());
        }
        if (orderDetailDto.fecPed() != null) {
            existingOrder.setFecPed(orderDetailDto.fecPed());
        }

        orderRepository.save(existingOrder);
    }

    @Override
    public OrderDetailDto findById(Integer codPed) {
        Order order = orderRepository.findById(codPed)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for ID: " + codPed));

        return new OrderDetailDto(
                order.getCodPed(),
                order.getUser().getCodUsu(),
                order.getTotPed(),
                order.getContPed(),
                order.getTelPed(),
                order.getDirPed(),
                order.getEstPed(),
                order.getFecPed()
        );
    }

    @Override
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<Order> orders = orderRepository.spGetAllOrders(limit, offset);

        List<OrderDto> orderDtos = orders.stream().map(this::convertToDto).collect(Collectors.toList());

        long totalOrders = orderRepository.count();

        return new PageImpl<>(orderDtos, pageable, totalOrders);
    }

    private OrderDto convertToDto(Order order) {
        return new OrderDto(
                order.getCodPed(),
                order.getTotPed(),
                order.getContPed(),
                order.getDirPed(),
                order.getEstPed()

        );
    }
}
