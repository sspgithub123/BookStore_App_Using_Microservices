package com.bridgelabz.cart.service;


import javax.validation.Valid;
import com.bridgelabz.cart.dto.CartDTO;
import com.bridgelabz.cart.entity.Cart;

import java.util.List;


public interface ICartService {

    Cart insertToCart(@Valid CartDTO dto);

    List<Cart> getAllCarts();

    Cart getCartByID(Integer cartId);

    Cart updateById(Integer cartId, @Valid CartDTO dto);

    Cart deleteById(Integer cartId);


}
