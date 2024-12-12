package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Procedure(procedureName = "sp_CreateProduct")
    void createProduct(
            @Param("nomProd") String nomProd,
            @Param("descProd") String descProd,
            @Param("codCat") Integer codCat,
            @Param("preProd") Double preProd,
            @Param("stockProd") Integer stockProd,
            @Param("imgProd") String imgProd
    );

    @Procedure(procedureName = "sp_UpdateProduct")
    void updateProduct(
            @Param("codProd") Integer codProd,
            @Param("nomProd") String nomProd,
            @Param("descProd") String descProd,
            @Param("codCat") Integer codCat,
            @Param("preProd") Double preProd,
            @Param("stockProd") Integer stockProd,
            @Param("imgProd") String imgProd,
            @Param("estProd") Boolean estProd,
            @Param("fecProd") Date fecProd
    );

    @Query(value = "CALL sp_GetAllProducts(:p_limit, :p_offset)", nativeQuery = true)
    List<Product> spGetAllProducts(@Param("p_limit") int limit, @Param("p_offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM Producto", nativeQuery = true)
    long count();
}
