package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

    // Procedimiento para crear un detalle de pedido
    @Procedure(procedureName = "sp_CreateOrderDetail")
    void createOrderDetail(
            @Param("codPed") Integer codPed,
            @Param("codProd") Integer codProd,
            @Param("canDetPed") Integer canDetPed
    );

    // Procedimiento para actualizar un detalle de pedido
    @Procedure(procedureName = "sp_UpdateOrderDetail")
    void updateOrderDetail(
            @Param("codDetPed") Integer codDetPed,
            @Param("codPed") Integer codPed,
            @Param("codProd") Integer codProd,
            @Param("canDetPed") Integer canDetPed,
            @Param("totDetPed") Double totDetPed
    );

    // Consulta nativa para listar todos los detalles de pedido
    @Query(value = "CALL sp_ListOrderDetails(:p_limit, :p_offset)", nativeQuery = true)
    List<OrderDetail> spGetAllOrderDetails(@Param("p_limit") int limit, @Param("p_offset") int offset);

    // Consulta para contar todos los registros en la tabla DetallePedido
    @Query(value = "SELECT COUNT(*) FROM DetallePedido", nativeQuery = true)
    long count();

    // Eliminar un detalle de pedido por su ID
    void deleteById(Integer codDetPed);
}