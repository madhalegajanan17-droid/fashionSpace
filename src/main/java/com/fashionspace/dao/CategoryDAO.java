package com.fashionspace.dao;

import java.util.List;

import com.fashionspace.model.Category;

public interface CategoryDAO {

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int categoryId);

    Category getCategoryById(int categoryId);

    Category getCategoryByName(String categoryName);

    List<Category> getAllCategories();

    boolean categoryExists(String categoryName);
}