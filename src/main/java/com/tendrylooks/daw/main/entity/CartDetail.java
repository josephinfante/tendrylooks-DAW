package com.tendrylooks.daw.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "DetalleCarrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codDetCarr;
    //private Integer codCarr;
    private Integer codProd;
    private Integer canDetCarr;

    @ManyToMany
    @JoinTable(
            name = "detallecarrito_producto",
            joinColumns = @JoinColumn(name = "codDetCarr"),
            inverseJoinColumns = @JoinColumn(name = "codProd")
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "codCarr")
    private Cart cart;
}
