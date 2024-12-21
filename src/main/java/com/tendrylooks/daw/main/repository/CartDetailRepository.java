package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    @Procedure(procedureName = "sp_CreateCartDetail")
    void createCartDetail(
            @Param("codCarr") Integer codCarr,
            @Param("codProd") Integer codProd,
            @Param("canDetCarr") Integer canDetCarr
    );

    @Procedure(procedureName = "sp_UpdateCartDetail")
    void updateCartDetail(
            @Param("codDetCarr") Integer codDetCarr,
            @Param("codCarr") Integer codCarr,
            @Param("codProd") Integer codProd,
            @Param("canDetCarr") Integer canDetCarr
    );

    @Query(value = "CALL sp_GetAllCartDetails(:p_limit, :p_offset)", nativeQuery = true)
    List<CartDetail> spGetAllCartDetails(@Param("p_limit") int limit, @Param("p_offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM DetalleCarrito", nativeQuery = true)
    long count();
}
