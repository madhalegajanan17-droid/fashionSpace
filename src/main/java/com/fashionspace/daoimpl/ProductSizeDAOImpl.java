package com.fashionspace.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fashionspace.dao.ProductSizeDAO;
import com.fashionspace.model.ProductSize;
import com.fashionspace.util.DBConnection;

public class ProductSizeDAOImpl implements ProductSizeDAO {

    private Connection connection;

    public ProductSizeDAOImpl() {

        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addProductSize(ProductSize productSize) {

        String query =
                "INSERT INTO product_sizes(product_id,size_label,"
              + "stock_quantity,unit_price,is_available) "
              + "VALUES(?,?,?,?,?)";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, productSize.getProductId());
            pstmt.setString(2, productSize.getSizeLabel());
            pstmt.setInt(3, productSize.getStockQuantity());
            pstmt.setDouble(4, productSize.getUnitPrice());
            pstmt.setBoolean(5, productSize.isAvailable());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateProductSize(ProductSize productSize) {

        String query =
                "UPDATE product_sizes SET product_id=?,size_label=?,"
              + "stock_quantity=?,unit_price=?,is_available=? "
              + "WHERE product_size_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, productSize.getProductId());
            pstmt.setString(2, productSize.getSizeLabel());
            pstmt.setInt(3, productSize.getStockQuantity());
            pstmt.setDouble(4, productSize.getUnitPrice());
            pstmt.setBoolean(5, productSize.isAvailable());
            pstmt.setInt(6, productSize.getProductSizeId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProductSize(int productSizeId) {

        String query =
                "DELETE FROM product_sizes WHERE product_size_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, productSizeId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ProductSize getProductSizeById(int productSizeId) {

        String query =
                "SELECT * FROM product_sizes WHERE product_size_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, productSizeId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                ProductSize productSize = new ProductSize();

                productSize.setProductSizeId(rs.getInt("product_size_id"));
                productSize.setProductId(rs.getInt("product_id"));
                productSize.setSizeLabel(rs.getString("size_label"));
                productSize.setStockQuantity(rs.getInt("stock_quantity"));
                productSize.setUnitPrice(rs.getDouble("unit_price"));
                productSize.setAvailable(rs.getBoolean("is_available"));

                return productSize;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ProductSize> getSizesByProductId(int productId) {

        List<ProductSize> sizes = new ArrayList<>();

        String query =
                "SELECT * FROM product_sizes WHERE product_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                ProductSize productSize = new ProductSize();

                productSize.setProductSizeId(rs.getInt("product_size_id"));
                productSize.setProductId(rs.getInt("product_id"));
                productSize.setSizeLabel(rs.getString("size_label"));
                productSize.setStockQuantity(rs.getInt("stock_quantity"));
                productSize.setUnitPrice(rs.getDouble("unit_price"));
                productSize.setAvailable(rs.getBoolean("is_available"));

                sizes.add(productSize);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sizes;
    }

    @Override
    public ProductSize getProductBySize(int productId,
                                        String sizeLabel) {

        String query =
                "SELECT * FROM product_sizes "
              + "WHERE product_id=? AND size_label=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, productId);
            pstmt.setString(2, sizeLabel);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                ProductSize productSize = new ProductSize();

                productSize.setProductSizeId(rs.getInt("product_size_id"));
                productSize.setProductId(rs.getInt("product_id"));
                productSize.setSizeLabel(rs.getString("size_label"));
                productSize.setStockQuantity(rs.getInt("stock_quantity"));
                productSize.setUnitPrice(rs.getDouble("unit_price"));
                productSize.setAvailable(rs.getBoolean("is_available"));

                return productSize;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateStock(int productSizeId,
                               int stockQuantity) {

        String query =
                "UPDATE product_sizes SET stock_quantity=? "
              + "WHERE product_size_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, stockQuantity);
            pstmt.setInt(2, productSizeId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isStockAvailable(int productSizeId) {

        String query =
                "SELECT stock_quantity FROM product_sizes "
              + "WHERE product_size_id=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, productSizeId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("stock_quantity") > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<ProductSize> getAllProductSizes() {

        List<ProductSize> sizes = new ArrayList<>();

        String query = "SELECT * FROM product_sizes";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                ProductSize productSize = new ProductSize();

                productSize.setProductSizeId(rs.getInt("product_size_id"));
                productSize.setProductId(rs.getInt("product_id"));
                productSize.setSizeLabel(rs.getString("size_label"));
                productSize.setStockQuantity(rs.getInt("stock_quantity"));
                productSize.setUnitPrice(rs.getDouble("unit_price"));
                productSize.setAvailable(rs.getBoolean("is_available"));

                sizes.add(productSize);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sizes;
    }
}