package com.tendrylooks.daw.main.dto;

import java.util.List;

public record GetAllResponseDto<T>(List<T> items,
                                   int pageSize,
                                   int pageNumber,
                                   long totalCount, long totalPages) {
}
