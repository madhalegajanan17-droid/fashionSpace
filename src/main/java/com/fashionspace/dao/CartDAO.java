package com.fashionspace.dao;

import java.util.List;
import com.fashionspace.model.CartItem;

public interface CartDAO {

    void addToCart(int userId, int productId, String sizeLabel, int quantity);

    List<CartItem> getCartItemsByUserId(int userId);

    void removeCartItem(int cartItemId);

    double getCartTotal(int userId);
}