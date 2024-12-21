package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.OrderAllDto;
import com.tendrylooks.daw.main.dto.OrderDetailCreateDto;
import com.tendrylooks.daw.main.dto.OrderDetailDetailDto;
import com.tendrylooks.daw.main.dto.OrderDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDetailService {
    // Crear un nuevo detalle de pedido
    void createOrderDetail(OrderDetailCreateDto orderDetailCreateDto);

    // Actualizar un detalle de pedido existente
    void updateOrderDetail(OrderDetailDetailDto orderDetailDetailDto);

    // Buscar un detalle de pedido por su ID
    OrderDetailDetailDto findById(Integer codDetPed);

    // Listar todos los detalles de pedido con paginación
    Page<OrderAllDto> getAllOrderDetails(Pageable pageable);
}