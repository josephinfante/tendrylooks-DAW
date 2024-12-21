package com.tendrylooks.daw.main.dto;

import java.time.LocalDateTime;

public record UserDetailDto(Integer codUsu,
                            String nomUsu,
                            String apeUsu,
                            String correoUsu,
                            Integer rolUsu,
                            Integer estUsu,
                            LocalDateTime fecUsu) {
}
