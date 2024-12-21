package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.CartCreateDto;
import com.tendrylooks.daw.main.dto.CartDto;
import com.tendrylooks.daw.main.dto.CartForDetailDto;
import com.tendrylooks.daw.main.entity.Cart;
import com.tendrylooks.daw.main.entity.User;
import com.tendrylooks.daw.main.repository.CartRepository;
import com.tendrylooks.daw.main.repository.UserRepository;
import com.tendrylooks.daw.main.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createCart(CartCreateDto cartCreateDto) {
        cartRepository.createCart(
                cartCreateDto.codUsu()
        );
    }

    @Override
    public void updateCart(CartForDetailDto cartForDetailDto) {
        Cart existingCart = cartRepository.findById(cartForDetailDto.codCarr())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for ID: " + cartForDetailDto.codCarr()));

        if (cartForDetailDto.codCarr() != null) {
            User user = userRepository.findById(cartForDetailDto.codUsu())
                    .orElseThrow(() -> new IllegalArgumentException("Cart not found for ID: " + cartForDetailDto.codUsu()));
            existingCart.setUser(user);
        }
        if (cartForDetailDto.estCarr() != null) {
            existingCart.setEstCarr(cartForDetailDto.estCarr());
        }
        if (cartForDetailDto.fecCarr() != null) {
            existingCart.setFecCarr(cartForDetailDto.fecCarr());
        }

        cartRepository.save(existingCart);
    }

    @Override
    public CartForDetailDto findById(Integer codCarr) {
        Cart cart = cartRepository.findById(codCarr)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for ID: " + codCarr));

        return new CartForDetailDto(
                cart.getCodCarr(),
                cart.getUser().getCodUsu(),
                cart.getEstCarr(),
                cart.getFecCarr()
        );
    }

    @Override
    public Page<CartDto> getAllCarts(Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<Cart> carts = cartRepository.spGetAllCarts(limit, offset);

        List<CartDto> cartDto = carts.stream().map(this::convertToDto).collect(Collectors.toList());

        long totalCarts = cartRepository.count();

        return new PageImpl<>(cartDto, pageable, totalCarts);
    }

    private CartDto convertToDto(Cart cart) {
        return new CartDto(
                cart.getCodCarr(),
                cart.getUser().getCodUsu(),
                cart.getEstCarr()
        );
    }
}
