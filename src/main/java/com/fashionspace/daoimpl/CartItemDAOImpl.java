package com.fashionspace.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fashionspace.dao.CartItemDAO;
import com.fashionspace.model.CartItem;
import com.fashionspace.util.DBConnection;

public class CartItemDAOImpl implements CartItemDAO {

    private Connection connection;

    public CartItemDAOImpl() {

        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CartItem mapCartItem(ResultSet rs) throws SQLException {

        CartItem cartItem = new CartItem();

        cartItem.setCartItemId(rs.getInt("cart_item_id"));
        cartItem.setCartId(rs.getInt("cart_id"));
        cartItem.setProductId(rs.getInt("product_id"));
        cartItem.setSizeLabel(rs.getString("size_label"));
        cartItem.setQuantity(rs.getInt("quantity"));
        cartItem.setUnitPrice(rs.getDouble("unit_price"));

        return cartItem;
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {

        String query =
                "INSERT INTO cart_items(cart_id, product_id, size_label, quantity, unit_price) "
              + "VALUES(?,?,?,?,?)";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartItem.getCartId());
            pstmt.setInt(2, cartItem.getProductId());
            pstmt.setString(3, cartItem.getSizeLabel());
            pstmt.setInt(4, cartItem.getQuantity());
            pstmt.setDouble(5, cartItem.getUnitPrice());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {

        String query =
                "UPDATE cart_items SET cart_id=?, product_id=?, size_label=?, quantity=?, unit_price=? "
              + "WHERE cart_item_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartItem.getCartId());
            pstmt.setInt(2, cartItem.getProductId());
            pstmt.setString(3, cartItem.getSizeLabel());
            pstmt.setInt(4, cartItem.getQuantity());
            pstmt.setDouble(5, cartItem.getUnitPrice());
            pstmt.setInt(6, cartItem.getCartItemId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCartItem(int cartItemId) {

        String query =
                "DELETE FROM cart_items WHERE cart_item_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartItemId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public CartItem getCartItemById(int cartItemId) {

        String query =
                "SELECT * FROM cart_items WHERE cart_item_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartItemId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapCartItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {

        List<CartItem> cartItems = new ArrayList<>();

        String query =
                "SELECT * FROM cart_items WHERE cart_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                cartItems.add(mapCartItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public CartItem getCartItem(int cartId,
                                int productId,
                                String sizeLabel) {

        String query =
                "SELECT * FROM cart_items "
              + "WHERE cart_id=? AND product_id=? AND size_label=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartId);
            pstmt.setInt(2, productId);
            pstmt.setString(3, sizeLabel);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapCartItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean increaseQuantity(int cartItemId) {

        String query =
                "UPDATE cart_items SET quantity=quantity+1 "
              + "WHERE cart_item_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartItemId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean decreaseQuantity(int cartItemId) {

        String query =
                "UPDATE cart_items SET quantity=quantity-1 "
              + "WHERE cart_item_id=? AND quantity>1";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartItemId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean clearCartItems(int cartId) {

        String query =
                "DELETE FROM cart_items WHERE cart_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getCartItemCount(int cartId) {

        String query =
                "SELECT COUNT(*) FROM cart_items WHERE cart_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, cartId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}