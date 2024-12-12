package com.tendrylooks.daw.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codProd;
    private String nomProd;
    private String descProd;
    private Double preProd;
    private Integer stockProd;
    private String imgProd;
    private Boolean estProd;
    private Date fecProd;

    @ManyToOne
    @JoinColumn(name = "codCat", nullable = false)
    private Category category;
}
