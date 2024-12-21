package com.tendrylooks.daw.main.dto;

public record OrderDto(
         Integer codPed,
         Double totPed,
         String contPed,
         String dirPed,
         String estPed
        ) {
}
