package com.tendrylooks.daw.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "Categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codCat;
    private String nomCat;
    private String estCat;
    private Date fecCat;

    @OneToMany(mappedBy = "category")
    private List<Product> products;


}
