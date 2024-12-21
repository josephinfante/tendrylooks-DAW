package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Procedure(procedureName = "sp_CreateUser")
    void createUser(
            @Param("nomUsu") String nomUsu,
            @Param("apeUsu") String apeUsu,
            @Param("correoUsu") String correoUsu,
            @Param("contraUsu") String contraUsu,
            @Param("rolUsu") int rolUsu
    );

    @Query(value = "CALL sp_GetAllUsers(:p_limit, :p_offset)", nativeQuery = true)
    List<User> spGetAllUsers(@Param("p_limit") int limit, @Param("p_offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM Usuario", nativeQuery = true)
    long count();

    Optional<User> findByCorreoUsu(String correoUsu);
}
