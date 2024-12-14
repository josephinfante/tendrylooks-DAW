package com.tendrylooks.daw.main.dto;

import java.time.LocalDateTime;

public record UserCreateDto(String nomUsu,
                            String apeUsu,
                            String correoUsu,
                            String contraUsu,
                            int rolUsu) {
}
