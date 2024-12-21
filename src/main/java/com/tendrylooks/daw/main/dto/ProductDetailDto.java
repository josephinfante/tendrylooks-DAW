package com.tendrylooks.daw.main.dto;

import java.util.Date;

public record ProductDetailDto(Integer codProd,
                               String nomProd,
                               String descProd,
                               Integer codCat,
                               String nomCat,
                               Double preProd,
                               Integer stockProd,
                               String imgProd,
                               Boolean estProd,
                               Date fecProd) {
}
