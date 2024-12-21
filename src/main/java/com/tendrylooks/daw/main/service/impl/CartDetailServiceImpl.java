package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.CartDetailCreateDto;
import com.tendrylooks.daw.main.dto.CartDetailDetailDto;
import com.tendrylooks.daw.main.dto.CartDetailDto;
import com.tendrylooks.daw.main.entity.Cart;
import com.tendrylooks.daw.main.entity.CartDetail;
import com.tendrylooks.daw.main.repository.CartDetailRepository;
import com.tendrylooks.daw.main.repository.CartRepository;
import com.tendrylooks.daw.main.service.CartDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void createCartDetail(CartDetailCreateDto cartDetailCreateDto) {
        cartDetailRepository.createCartDetail(
                cartDetailCreateDto.codCarr(),
                cartDetailCreateDto.codProd(),
                cartDetailCreateDto.canDetCarr()
        );
    }

    @Override
    public void updateCartDetail(CartDetailDetailDto cartDetailDetailDto) {
        CartDetail existingCartDetail = cartDetailRepository.findById(cartDetailDetailDto.codDetCarr())
                .orElseThrow(() -> new EntityNotFoundException("CartDetail not found for ID: " + cartDetailDetailDto.codDetCarr()));

        if (cartDetailDetailDto.codCarr() != null) {
            Cart cart = cartRepository.findById(cartDetailDetailDto.codCarr())
                    .orElseThrow(() -> new IllegalArgumentException("Cart not found for ID: " + cartDetailDetailDto.codCarr()));
            existingCartDetail.setCart(cart);
        }
        if (cartDetailDetailDto.codProd() != null) {
            existingCartDetail.setCodProd(cartDetailDetailDto.codProd());
        }
        if (cartDetailDetailDto.canDetCarr() != null) {
            existingCartDetail.setCanDetCarr(cartDetailDetailDto.canDetCarr());
        }

        cartDetailRepository.save(existingCartDetail);
    }

    @Override
    public CartDetailDetailDto findById(Integer codDetCarr) {
        CartDetail cartDetail = cartDetailRepository.findById(codDetCarr)
                .orElseThrow(() -> new EntityNotFoundException("CartDetail not found for ID: " + codDetCarr));

        return new CartDetailDetailDto(
                cartDetail.getCodDetCarr(),
                cartDetail.getCart().getCodCarr(),
                cartDetail.getCodProd(),
                cartDetail.getCanDetCarr()
        );
    }

    @Override
    public Page<CartDetailDto> getAllCartDetails(Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<CartDetail> cartDetails = cartDetailRepository.spGetAllCartDetails(limit, offset);

        List<CartDetailDto> cartDetailDto = cartDetails.stream().map(this::convertToDto).collect(Collectors.toList());

        long totalCartDetails = cartDetailRepository.count();

        return new PageImpl<>(cartDetailDto, pageable, totalCartDetails);
    }

    private CartDetailDto convertToDto(CartDetail cartDetail) {

        return new CartDetailDto(
                cartDetail.getCodDetCarr(),
                cartDetail.getCart().getCodCarr(),
                cartDetail.getCodProd(),
                cartDetail.getCanDetCarr()
        );
    }
}
