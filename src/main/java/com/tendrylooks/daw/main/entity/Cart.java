package com.tendrylooks.daw.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "Carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codCarr;
    //private Integer codUsu;
    private Integer estCarr;
    private Date fecCarr;

    @ManyToOne
    @JoinColumn(name = "codUsu")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetails;
}
