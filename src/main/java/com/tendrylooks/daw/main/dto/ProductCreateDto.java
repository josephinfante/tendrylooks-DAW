package com.tendrylooks.daw.main.dto;

import java.util.Date;

public record ProductCreateDto(Integer codProd,
                               String nomProd,
                               String descProd,
                               Integer codCat,
                               Double preProd,
                               Integer stockProd,
                               String imgProd) {
}
