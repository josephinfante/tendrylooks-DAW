package com.tendrylooks.daw.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "DetallePedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codDetPed;
    //private Integer codPed;
    private Integer codProd;
    private Integer canDetPed;
    private Double totDetPed;

    @ManyToMany
    @JoinTable(
            name = "detallepedido_producto",
            joinColumns = @JoinColumn(name = "codDetPed"),
            inverseJoinColumns = @JoinColumn(name = "codProd")
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "codPed")
    private Order order;
}
