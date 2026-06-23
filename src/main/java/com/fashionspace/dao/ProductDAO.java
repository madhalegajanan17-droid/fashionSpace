package com.fashionspace.dao;

import java.util.List;

import com.fashionspace.model.Product;

public interface ProductDAO {

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> getLatestProducts();

    List<Product> getActiveProducts();
    
    List<Product> getAllProducts(String sort);

    boolean updateProductStatus(int productId, boolean status);

    int getProductCount();
}
