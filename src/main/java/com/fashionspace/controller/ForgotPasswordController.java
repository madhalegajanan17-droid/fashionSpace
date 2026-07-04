package com.fashionspace.controller;

import java.io.IOException;

import com.fashionspace.dao.UserDAO;
import com.fashionspace.daoimpl.UserDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("sendOtp".equals(action)) { 
            String email = request.getParameter("email");
            boolean userExists = userDAO.isEmailExists(email);

            if (userExists) {
                session.setAttribute("email", email); 
                session.setAttribute("success", "Email verified. Now reset your password");
                response.sendRedirect(request.getContextPath() + "/forgot-password");
                
            } else {
                request.setAttribute("error", "Wrong Email! This email is not registered");
                request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp")
                        .forward(request, response);
            }
        } 
        else if ("resetPassword".equals(action)) { 
            String newPassword = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String sessionEmail = (String) session.getAttribute("email");

            if (sessionEmail != null && newPassword.equals(confirmPassword)) {
                boolean updated = userDAO.updatePassword(sessionEmail, newPassword);
                if(updated){
                    session.invalidate();
                    // YE LINE IMPORTANT HAI: Direct login pe bhejna hai
                    response.sendRedirect(request.getContextPath() + "/login?msg=Password+Updated+Successfully");
                    return;
                } else {
                    request.setAttribute("error", "Failed to update password");
                }
            } else {
                request.setAttribute("error", "Passwords do not match");
            }
            request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp")
                    .forward(request, response);
        }
    }
}