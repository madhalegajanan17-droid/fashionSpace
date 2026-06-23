package com.fashionspace.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fashionspace.dao.CategoryDAO;
import com.fashionspace.model.Category;
import com.fashionspace.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    private Connection connection;

    public CategoryDAOImpl() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addCategory(Category category) {
        String query = "INSERT INTO categories(category_name, description) VALUES(?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, category.getCategoryName());
            pstmt.setString(2, category.getDescription());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateCategory(Category category) {
        String query = "UPDATE categories SET category_name=?, description=? WHERE category_id=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, category.getCategoryName());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, category.getCategoryId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        String query = "DELETE FROM categories WHERE category_id=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setInt(1, categoryId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        String query = "SELECT * FROM categories WHERE category_id=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setInt(1, categoryId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));

                return category;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        String query = "SELECT * FROM categories WHERE category_name=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, categoryName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));

                return category;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        String query = "SELECT * FROM categories";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));

                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public boolean categoryExists(String categoryName) {
        String query = "SELECT * FROM categories WHERE category_name=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, categoryName);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}