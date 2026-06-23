package com.fashionspace.dao;

import java.util.List;

import com.fashionspace.model.CartItem;

public interface CartItemDAO {

    boolean addCartItem(CartItem cartItem);

    boolean updateCartItem(CartItem cartItem);

    boolean deleteCartItem(int cartItemId);

    CartItem getCartItemById(int cartItemId);

    List<CartItem> getCartItemsByCartId(int cartId);

    CartItem getCartItem(int cartId,
                         int productId,
                         String sizeLabel);

    boolean increaseQuantity(int cartItemId);

    boolean decreaseQuantity(int cartItemId);

    boolean clearCartItems(int cartId);

    int getCartItemCount(int cartId);
}