package com.fashionspace.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fashionspace.dao.OrderDAO;
import com.fashionspace.model.CartItem;
import com.fashionspace.model.Order;
import com.fashionspace.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int placeOrder(int userId,
                          String paymentMethod,
                          double totalAmount,
                          List<CartItem> cartItems) {

        int orderId = 0;

        try (Connection con = DBConnection.getConnection()) {

            String orderQuery =
                    "INSERT INTO orders(user_id, total_amount, order_status, payment_method) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, userId);
            ps.setDouble(2, totalAmount);
            ps.setString(3, "PLACED");
            ps.setString(4, paymentMethod);

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {
                orderId = keys.getInt(1);
            }

            String itemQuery =
                    "INSERT INTO order_items(order_id, product_id, size_label, quantity, unit_price) VALUES(?,?,?,?,?)";

            PreparedStatement itemPs =
                    con.prepareStatement(itemQuery);

            for (CartItem item : cartItems) {

                itemPs.setInt(1, orderId);
                itemPs.setInt(2, item.getProductId());
                itemPs.setString(3, item.getSizeLabel());
                itemPs.setInt(4, item.getQuantity());
                itemPs.setDouble(5, item.getUnitPrice());

                itemPs.executeUpdate();
            }

            String deleteQuery =
                    "DELETE ci FROM cart_items ci " +
                    "JOIN cart c ON ci.cart_id = c.cart_id " +
                    "WHERE c.user_id = ?";

            PreparedStatement deletePs =
                    con.prepareStatement(deleteQuery);

            deletePs.setInt(1, userId);
            deletePs.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String query =
                    "SELECT * FROM orders WHERE user_id = ? ORDER BY order_id DESC";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTotalAmount(rs.getDouble("total_amount"));

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}