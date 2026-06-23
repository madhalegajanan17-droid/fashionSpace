package com.fashionspace.dao;

import java.util.List;

import com.fashionspace.model.ProductSize;

public interface ProductSizeDAO {

    boolean addProductSize(ProductSize productSize);

    boolean updateProductSize(ProductSize productSize);

    boolean deleteProductSize(int productSizeId);

    ProductSize getProductSizeById(int productSizeId);

    List<ProductSize> getSizesByProductId(int productId);

    ProductSize getProductBySize(int productId,
                                 String sizeLabel);

    boolean updateStock(int productSizeId,
                        int stockQuantity);

    boolean isStockAvailable(int productSizeId);

    List<ProductSize> getAllProductSizes();
}