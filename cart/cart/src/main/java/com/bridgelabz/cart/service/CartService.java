package com.bridgelabz.cart.service;

import javax.validation.Valid;

import com.bridgelabz.cart.entity.Cart;
import com.bridgelabz.cart.exception.CartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.cart.dto.CartDTO;
import com.bridgelabz.cart.repository.CartRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepository repo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Cart insertToCart(@Valid CartDTO dto) {
        Cart cart = new Cart(dto);
        repo.save(cart);
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        List<Cart> list = repo.findAll();
        if(list.isEmpty()) {
            throw new NullPointerException("there are no Carts inserted yet");
        }
        return list;
    }

    @Override
    public Cart getCartByID(Integer cartId) {
        Optional<Cart> cart = repo.findById(cartId);
        if(cart.isEmpty()) {
            throw new CartException("There are no carts with given id");
        }
        return cart.get();
    }

    @Override
    public Cart updateById(Integer cartId, @Valid CartDTO dto) {
        Optional<Cart> cart = repo.findById(cartId);
        if(cart.isPresent()) {
            Cart newCart = new Cart(cartId,dto);
            repo.save(newCart);
            return newCart;
        }
        else {
            throw new CartException("cart not found");
        }
    }

    @Override
    public Cart deleteById(Integer cartId) {
        Optional<Cart> cart = repo.findById(cartId);
        if(cart.isEmpty()) {
            throw new CartException("Invalid CartID..please input valid Id");
        }
        repo.deleteById(cartId);
        return cart.get();
    }

}

