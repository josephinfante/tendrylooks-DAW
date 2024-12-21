package com.tendrylooks.daw.main.dto;

import java.util.Date;

public record CartForDetailDto(
        Integer codCarr,
        Integer codUsu,
        Integer estCarr,
        Date fecCarr
) {
}
