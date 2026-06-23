package com.fashionspace.controller;

import java.io.IOException;
import java.util.List;

import com.fashionspace.dao.CartDAO;
import com.fashionspace.dao.OrderDAO;
import com.fashionspace.daoimpl.CartDAOImpl;
import com.fashionspace.daoimpl.OrderDAOImpl;
import com.fashionspace.model.CartItem;
import com.fashionspace.model.User;
import com.fashionspace.util.EmailUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/place-order")
public class PlaceOrderController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {

        cartDAO = new CartDAOImpl();
        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        int userId =
                (Integer) session.getAttribute("userId");

        String paymentMethod =
                request.getParameter("paymentMethod");

        // ================= PAYMENT VERIFICATION =================

        if (paymentMethod == null ||
                paymentMethod.trim().isEmpty()) {

            paymentMethod = "COD";
        }

        // ================= UPI VERIFICATION =================

        if (paymentMethod.equals("UPI")) {

            String upiId =
                    request.getParameter("upiId");

            if (upiId == null ||
                    upiId.trim().isEmpty() ||
                    !upiId.contains("@")) {

                response.getWriter()
                        .println("UPI Payment Failed");

                return;
            }
        }

        // ================= CARD VERIFICATION =================

        if (paymentMethod.equals("CARD")) {

            String cardNumber =
                    request.getParameter("cardNumber");

            String expiry =
                    request.getParameter("expiry");

            String cvv =
                    request.getParameter("cvv");

            if (cardNumber == null ||
                    cardNumber.trim().length() < 16 ||
                    expiry == null ||
                    expiry.trim().isEmpty() ||
                    cvv == null ||
                    cvv.trim().length() < 3) {

                response.getWriter()
                        .println("Card Payment Failed");

                return;
            }
        }

        // ================= PAYMENT SUCCESS =================

        System.out.println("Payment Successful");

        // ================= YOUR EXISTING CODE =================

        List<CartItem> cartItems =
                cartDAO.getCartItemsByUserId(userId);

        if (cartItems == null ||
                cartItems.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        double grandTotal =
                cartDAO.getCartTotal(userId);

        int orderId =
                orderDAO.placeOrder(
                        userId,
                        paymentMethod,
                        grandTotal,
                        cartItems
                );

        // ================= EMAIL SEND =================

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user != null && orderId > 0) {

            EmailUtility.sendOrderSuccessEmail(
                    user.getEmail(),
                    orderId,
                    grandTotal
            );
        }

        // ================= SUCCESS PAGE =================

        request.setAttribute("orderId", orderId);

        request.getRequestDispatcher(
                "/WEB-INF/views/order-success.jsp")
                .forward(request, response);
    }
}