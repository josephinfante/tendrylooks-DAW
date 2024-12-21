package com.tendrylooks.daw.main.dto;

import java.util.Date;

public record CategoryDetailDto(
        Integer codCat,
        String nomCat,
        Boolean estCat,
        Date fecCat) {
}
