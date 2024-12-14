package com.tendrylooks.daw.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codUsu;

    private String nomUsu;
    private String apeUsu;
    private String correoUsu;
    private String contraUsu;
    private int rolUsu; // 0: Usuario, 1: Administrador
    private int estUsu; // 0: Inactivo, 1: Activo
    private LocalDateTime fecUsu;
}
