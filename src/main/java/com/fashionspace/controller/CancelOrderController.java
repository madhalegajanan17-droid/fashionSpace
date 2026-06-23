package com.fashionspace.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fashionspace.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancel-order")
public class CancelOrderController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(request.getParameter("orderId"));

        try {

            Connection con =
                    DBConnection.getConnection();

            String deleteItems =
                    "DELETE FROM order_items WHERE order_id=?";

            PreparedStatement ps1 =
                    con.prepareStatement(deleteItems);

            ps1.setInt(1, orderId);
            ps1.executeUpdate();

            String deleteOrder =
                    "DELETE FROM orders WHERE order_id=?";

            PreparedStatement ps2 =
                    con.prepareStatement(deleteOrder);

            ps2.setInt(1, orderId);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath() + "/orders");
    }
}