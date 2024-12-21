package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Procedure(procedureName = "sp_CreateCart")
    void createCart(
            @Param("codUsu") Integer codUsu
    );

    @Procedure(procedureName = "sp_UpdateCart")
    void updateCart(
            @Param("codCarr") Integer codCarr,
            @Param("codUsu") Integer codUsu,
            @Param("estCarr") Boolean estCarr,
            @Param("fecCarr") Date fecCarr
    );

    @Query(value = "CALL sp_GetAllCarts(:p_limit, :p_offset)", nativeQuery = true)
    List<Cart> spGetAllCarts(@Param("p_limit") int limit, @Param("p_offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM Carrito", nativeQuery = true)
    long count();
}
