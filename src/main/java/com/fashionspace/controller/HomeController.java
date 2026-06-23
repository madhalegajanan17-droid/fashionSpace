package com.fashionspace.controller;

import java.io.IOException;
import java.util.List;

import com.fashionspace.dao.ProductDAO;
import com.fashionspace.daoimpl.ProductDAOImpl;
import com.fashionspace.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> latestProducts =
                productDAO.getLatestProducts();

        request.setAttribute(
                "latestProducts",
                latestProducts);

        request.getRequestDispatcher(
                "/WEB-INF/views/home.jsp")
                .forward(request, response);
    }
}