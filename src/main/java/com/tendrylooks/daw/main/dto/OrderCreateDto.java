package com.tendrylooks.daw.main.dto;

public record OrderCreateDto(
        Integer codPed,
        Integer codUsu,
        Double totPed,
        String contPed,
        String telPed,
        String dirPed
) {
}
