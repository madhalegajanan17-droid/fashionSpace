package com.fashionspace.controller;

import java.io.IOException;
import java.util.List;

import com.fashionspace.dao.CartDAO;
import com.fashionspace.daoimpl.CartDAOImpl;
import com.fashionspace.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        cartDAO = new CartDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Integer userId =
                (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if ("remove".equals(action)) {

            int cartItemId =
                    Integer.parseInt(request.getParameter("cartItemId"));

            cartDAO.removeCartItem(cartItemId);

            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        List<CartItem> cartItems =
                cartDAO.getCartItemsByUserId(userId);

        request.setAttribute("cartItems", cartItems);

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Integer userId =
                (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int productId =
                Integer.parseInt(request.getParameter("productId"));

        String sizeLabel =
                request.getParameter("sizeLabel");

        int quantity =
                Integer.parseInt(request.getParameter("quantity"));

        cartDAO.addToCart(userId, productId, sizeLabel, quantity);

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}