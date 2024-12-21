package com.tendrylooks.daw.main.dto;

import java.util.Date;

public record OrderDetailDto(
        Integer codPed,
        Integer codUsu,
        Double totPed,
        String contPed,
        String telPed,
        String dirPed,
        String estPed,
        Date fecPed
) {
}
