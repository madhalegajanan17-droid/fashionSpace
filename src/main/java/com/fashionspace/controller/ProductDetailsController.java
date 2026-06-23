package com.fashionspace.controller;

import java.io.IOException;

import com.fashionspace.dao.ProductDAO;
import com.fashionspace.daoimpl.ProductDAOImpl;
import com.fashionspace.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product-details")
public class ProductDetailsController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String id =
                    request.getParameter("id");

            if (id == null || id.isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/products");

                return;
            }

            int productId =
                    Integer.parseInt(id);

            Product product =
                    productDAO.getProductById(productId);

            request.setAttribute(
                    "product",
                    product);

            request.getRequestDispatcher(
                    "/WEB-INF/views/product-details.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/products");
        }
    }
}