package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.CartCreateDto;
import com.tendrylooks.daw.main.dto.CartDto;
import com.tendrylooks.daw.main.dto.CartForDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {
    void createCart(CartCreateDto cartCreateDto);
    void updateCart(CartForDetailDto cartForDetailDto);
    CartForDetailDto findById(Integer codCarr);
    Page<CartDto> getAllCarts(Pageable pageable);
}
