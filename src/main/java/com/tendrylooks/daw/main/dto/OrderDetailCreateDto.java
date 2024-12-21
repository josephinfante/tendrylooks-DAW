package com.tendrylooks.daw.main.dto;

public record OrderDetailCreateDto( Integer codDetPed,
                                    Integer codPed,
                                    Integer codProd,
                                    Integer canDetPed,
                                    Double totDetPed) {
}
