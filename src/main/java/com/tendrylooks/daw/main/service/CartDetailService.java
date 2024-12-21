package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.CartDetailCreateDto;
import com.tendrylooks.daw.main.dto.CartDetailDetailDto;
import com.tendrylooks.daw.main.dto.CartDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartDetailService {
    void createCartDetail(CartDetailCreateDto cartDetailCreateDto);
    void updateCartDetail(CartDetailDetailDto cartDetailDetailDto);
    CartDetailDetailDto findById(Integer codDetCarr);
    Page<CartDetailDto> getAllCartDetails(Pageable pageable);
}
