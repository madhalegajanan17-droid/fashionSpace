package com.fashionspace.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fashionspace.dao.OrderItemDAO;
import com.fashionspace.model.OrderItem;
import com.fashionspace.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private Connection connection;

    public OrderItemDAOImpl() {

        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrderItem mapOrderItem(ResultSet rs)
            throws SQLException {

        OrderItem orderItem = new OrderItem();

        orderItem.setOrderItemId(
                rs.getInt("order_item_id"));

        orderItem.setOrderId(
                rs.getInt("order_id"));

        orderItem.setProductId(
                rs.getInt("product_id"));

        orderItem.setSizeLabel(
                rs.getString("size_label"));

        orderItem.setQuantity(
                rs.getInt("quantity"));

        orderItem.setUnitPrice(
                rs.getDouble("unit_price"));

        return orderItem;
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        String query =
                "INSERT INTO order_items(order_id, product_id, "
              + "size_label, quantity, unit_price) "
              + "VALUES(?,?,?,?,?)";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1,
                    orderItem.getOrderId());

            pstmt.setInt(2,
                    orderItem.getProductId());

            pstmt.setString(3,
                    orderItem.getSizeLabel());

            pstmt.setInt(4,
                    orderItem.getQuantity());

            pstmt.setDouble(5,
                    orderItem.getUnitPrice());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {

        String query =
                "UPDATE order_items SET order_id=?, "
              + "product_id=?, size_label=?, quantity=?, "
              + "unit_price=? WHERE order_item_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1,
                    orderItem.getOrderId());

            pstmt.setInt(2,
                    orderItem.getProductId());

            pstmt.setString(3,
                    orderItem.getSizeLabel());

            pstmt.setInt(4,
                    orderItem.getQuantity());

            pstmt.setDouble(5,
                    orderItem.getUnitPrice());

            pstmt.setInt(6,
                    orderItem.getOrderItemId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {

        String query =
                "DELETE FROM order_items "
              + "WHERE order_item_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, orderItemId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        String query =
                "SELECT * FROM order_items "
              + "WHERE order_item_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, orderItemId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapOrderItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(
            int orderId) {

        List<OrderItem> orderItems =
                new ArrayList<>();

        String query =
                "SELECT * FROM order_items "
              + "WHERE order_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItems.add(mapOrderItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {

        List<OrderItem> orderItems =
                new ArrayList<>();

        String query =
                "SELECT * FROM order_items";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItems.add(mapOrderItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public int getOrderItemCount(int orderId) {

        String query =
                "SELECT COUNT(*) FROM order_items "
              + "WHERE order_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double getTotalAmount(int orderId) {

        String query =
                "SELECT SUM(unit_price * quantity) "
              + "FROM order_items WHERE order_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}