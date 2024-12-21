package com.tendrylooks.daw.main.repository;

import com.tendrylooks.daw.main.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
