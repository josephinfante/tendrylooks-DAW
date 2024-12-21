package com.tendrylooks.daw.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "Pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codPed;
    //private Integer codUsu;
    private Double totPed;
    private String contPed;
    private String telPed;
    private String dirPed;
    private String estPed;
    private Date fecPed;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "codUsu")
    private User user;
}
