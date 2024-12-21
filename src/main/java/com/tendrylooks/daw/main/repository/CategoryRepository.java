package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Procedure(procedureName = "sp_CreateCategory")
    void createCategory(
            @Param("nomCat") String nomCat
    );

    @Procedure(procedureName = "sp_UpdateCategory")
    void updateCategory(
            @Param("codCat") Integer codCat,
            @Param("nomCat") String nomCat,
            @Param("estCat") Boolean estCat,
            @Param("fecCat") Date fecCat
    );

    @Query(value = "CALL sp_GetAllCategories(:p_limit, :p_offset)", nativeQuery = true)
    List<Category> spGetAllCategories(@Param("p_limit") int limit, @Param("p_offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM Categoria", nativeQuery = true)
    long count();
}
