package com.fashionspace.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fashionspace.dao.CartDAO;
import com.fashionspace.model.CartItem;
import com.fashionspace.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    @Override
    public void addToCart(int userId, int productId, String sizeLabel, int quantity) {

        try (Connection con = DBConnection.getConnection()) {

            int cartId = 0;

            PreparedStatement ps = con.prepareStatement(
                    "SELECT cart_id FROM cart WHERE user_id=?"
            );

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                cartId = rs.getInt("cart_id");

            } else {

                PreparedStatement ps2 = con.prepareStatement(
                        "INSERT INTO cart(user_id) VALUES(?)",
                        Statement.RETURN_GENERATED_KEYS
                );

                ps2.setInt(1, userId);
                ps2.executeUpdate();

                ResultSet keys = ps2.getGeneratedKeys();

                if (keys.next()) {
                    cartId = keys.getInt(1);
                }
            }

            double unitPrice = 0;

            PreparedStatement ps3 = con.prepareStatement(
                    "SELECT unit_price FROM product_sizes WHERE product_id=? AND size_label=?"
            );

            ps3.setInt(1, productId);
            ps3.setString(2, sizeLabel);

            ResultSet priceRs = ps3.executeQuery();

            if (priceRs.next()) {
                unitPrice = priceRs.getDouble("unit_price");
            }

            PreparedStatement ps4 = con.prepareStatement(
                    "SELECT cart_item_id, quantity FROM cart_items WHERE cart_id=? AND product_id=? AND size_label=?"
            );

            ps4.setInt(1, cartId);
            ps4.setInt(2, productId);
            ps4.setString(3, sizeLabel);

            ResultSet itemRs = ps4.executeQuery();

            if (itemRs.next()) {

                int cartItemId = itemRs.getInt("cart_item_id");
                int oldQuantity = itemRs.getInt("quantity");

                PreparedStatement ps5 = con.prepareStatement(
                        "UPDATE cart_items SET quantity=?, unit_price=? WHERE cart_item_id=?"
                );

                ps5.setInt(1, oldQuantity + quantity);
                ps5.setDouble(2, unitPrice);
                ps5.setInt(3, cartItemId);

                ps5.executeUpdate();

            } else {

                PreparedStatement ps6 = con.prepareStatement(
                        "INSERT INTO cart_items(cart_id, product_id, size_label, quantity, unit_price) VALUES(?,?,?,?,?)"
                );

                ps6.setInt(1, cartId);
                ps6.setInt(2, productId);
                ps6.setString(3, sizeLabel);
                ps6.setInt(4, quantity);
                ps6.setDouble(5, unitPrice);

                ps6.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {

        List<CartItem> cartItems = new ArrayList<>();

        String query =
                "SELECT ci.* " +
                "FROM cart_items ci " +
                "JOIN cart c ON ci.cart_id = c.cart_id " +
                "WHERE c.user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setCartId(rs.getInt("cart_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSizeLabel(rs.getString("size_label"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));

                cartItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public void removeCartItem(int cartItemId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM cart_items WHERE cart_item_id=?"
             )) {

            ps.setInt(1, cartItemId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getCartTotal(int userId) {

        double total = 0;

        String query =
                "SELECT SUM(ci.quantity * ci.unit_price) AS total " +
                "FROM cart_items ci " +
                "JOIN cart c ON ci.cart_id = c.cart_id " +
                "WHERE c.user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
}