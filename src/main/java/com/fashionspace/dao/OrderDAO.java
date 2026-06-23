package com.fashionspace.dao;

import java.util.List;

import com.fashionspace.model.CartItem;
import com.fashionspace.model.Order;

public interface OrderDAO {

    int placeOrder(
        int userId,
        String paymentMethod,
        double totalAmount,
        List<CartItem> cartItems
    );

    List<Order> getOrdersByUserId(int userId);
}