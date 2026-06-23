package com.fashionspace.util;

import java.util.List;

import com.fashionspace.dao.ProductDAO;
import com.fashionspace.daoimpl.ProductDAOImpl;
import com.fashionspace.model.Product;

public class TestProductData {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAOImpl();

        List<Product> products =
                productDAO.getAllProducts();

        for (Product product : products) {

            System.out.println(
                    "Product ID : "
                    + product.getProductId());

            System.out.println(
                    "Product Name : "
                    + product.getProductName());

            System.out.println(
                    "Category ID : "
                    + product.getCategoryId());

            System.out.println(
                    "Color : "
                    + product.getColor());

            System.out.println(
                    "Discount : "
                    + product.getDiscountPercent());

            System.out.println(
                    "Image URL : "
                    + product.getImageUrl());

            System.out.println(
                    "Active : "
                    + product.isActive());

            System.out.println(
                    "--------------------------------");
        }
    }
}