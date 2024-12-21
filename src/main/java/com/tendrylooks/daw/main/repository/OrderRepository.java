package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Procedure(procedureName = "sp_CreateOrder")
    void createOrder(
            @Param("codUsu") Integer codUsu,
            @Param("totPed") Double totPed,
            @Param("contPed") String contPed,
            @Param("telPed") String telPed,
            @Param("dirPed") String dirPed
    );

    @Procedure(procedureName = "sp_UpdateOrder")
    void updateOrder(
            @Param("codPed") Integer codPed,
            @Param("codUsu") Integer codUsu,
            @Param("totPed") Double totPed,
            @Param("contPed") String contPed,
            @Param("telPed") String telPed,
            @Param("dirPed") String dirPed,
            @Param("estPed") String estPed,
            @Param("fecPed") Date fecPed
    );

    @Query(value = "CALL sp_GetAllOrders(:p_limit, :p_offset)", nativeQuery = true)
    List<Order> spGetAllOrders(@Param("p_limit") int limit, @Param("p_offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM Pedido", nativeQuery = true)
    long count();
}

