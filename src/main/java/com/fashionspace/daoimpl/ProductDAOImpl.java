package com.fashionspace.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionspace.dao.ProductDAO;
import com.fashionspace.model.Product;
import com.fashionspace.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;

    public ProductDAOImpl() {

        try {

            connection = DBConnection.getConnection();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }

    @Override
    public Product getProductById(int productId) {

        Product product = null;

        try {

            String query =
                    "SELECT * FROM products WHERE product_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setColor(rs.getString("color"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setPrice(rs.getDouble("price"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM products";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setColor(rs.getString("color"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setPrice(rs.getDouble("price"));

                products.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products = new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM products WHERE category_id=? AND is_active=true";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setColor(rs.getString("color"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setPrice(rs.getDouble("price"));

                products.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM products WHERE product_name LIKE ? AND is_active = true";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setColor(rs.getString("color"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setPrice(rs.getDouble("price"));

                products.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getLatestProducts() {

        List<Product> products = new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM products WHERE is_active=true ORDER BY product_id DESC LIMIT 8";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setColor(rs.getString("color"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setPrice(rs.getDouble("price"));

                products.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getActiveProducts() {

        List<Product> products = new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM products WHERE is_active=true";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setColor(rs.getString("color"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setPrice(rs.getDouble("price"));

                products.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getAllProducts(String sort) {

        List<Product> products = new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM products WHERE is_active=true";

            if ("lowToHigh".equals(sort)) {

                query += " ORDER BY price ASC";

            } else if ("highToLow".equals(sort)) {

                query += " ORDER BY price DESC";

            } else if ("newest".equals(sort)) {

                query += " ORDER BY product_id DESC";
            }

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setColor(rs.getString("color"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setPrice(rs.getDouble("price"));

                products.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public boolean updateProductStatus(int productId, boolean status) {
        return false;
    }

    @Override
    public int getProductCount() {
        return getAllProducts().size();
    }
}