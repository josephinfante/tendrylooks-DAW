package com.tendrylooks.daw.main.dto;

import java.util.Date;

public record ProductListingDto(Integer codProd,
                                String nomProd,
                                Double preProd,
                                Integer stockProd,
                                String imgProd,
                                Boolean estProd) {
}
