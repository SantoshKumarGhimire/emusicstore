package com.emusicstore.dao.impl;

import com.emusicstore.dao.CartDao;
import com.emusicstore.model.Cart;
import com.emusicstore.model.CartItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository

public class CartDaoImpl implements CartDao {
    private Map<String,Cart> carts;

    public CartDaoImpl() {
        this.carts = new HashMap<>();
    }

    @Override
    public Cart create(Cart cart) {

        if(carts.keySet().contains(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Cannot create. A cart with the" +
                    " given id(%)" +
                    "already exists", cart.getCartId()));
        }

        carts.put(cart.getCartId(),cart);
        return cart;
    }

    @Override
    public Cart read(String cartId) {
        return carts.get(cartId);
    }

    @Override
    public void update(String cartId, Cart cart) {

        if (!carts.keySet().contains(cartId)){
            throw  new IllegalArgumentException(String.format("Cannot update cart. The cart with the " +
                    "given id(%) dosenot exists",cartId));
        }

        carts.put(cartId,cart);

    }

    @Override
    public void delete(String cartId) {

        if (!carts.keySet().contains(cartId)){
            throw  new IllegalArgumentException(String.format("Cannot delete cart. The cart with the " +
                    "given id(%) dosenot exists",cartId));
        }

        carts.remove(cartId);


    }
}
