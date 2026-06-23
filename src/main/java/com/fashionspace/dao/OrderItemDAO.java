package com.fashionspace.dao;

import java.util.List;

import com.fashionspace.model.OrderItem;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem orderItem);

    boolean updateOrderItem(OrderItem orderItem);

    boolean deleteOrderItem(int orderItemId);

    OrderItem getOrderItemById(int orderItemId);

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    List<OrderItem> getAllOrderItems();

    int getOrderItemCount(int orderId);

    double getTotalAmount(int orderId);
}