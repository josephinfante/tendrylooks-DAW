package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

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

    Optional<User> findByCorreoUsu(String correoUsu);
}
