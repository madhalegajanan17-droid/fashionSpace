package com.fashionspace.controller;

import java.io.IOException;

import com.fashionspace.dao.UserDAO;
import com.fashionspace.daoimpl.UserDAOImpl;
import com.fashionspace.util.EmailUtility;

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
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("sendOtp".equals(action)) {

            String email = request.getParameter("email");
            session.setAttribute("email", email);

            boolean userExists = userDAO.isEmailExists(email);

            if (userExists) {

                String otp = String.valueOf(
                        (int) (Math.random() * 900000) + 100000
                );

                session.setAttribute("otp", otp);
                session.removeAttribute("otpVerified");
                session.removeAttribute("enteredOtp");

                boolean sent = EmailUtility.sendOTP(email, otp);

                if (sent) {
                    request.setAttribute("success",
                            "OTP sent successfully to your email");
                } else {
                    request.setAttribute("error",
                            "Failed to send OTP");
                }

            } else {
                request.setAttribute("error",
                        "Email not found");
            }

            request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp")
                   .forward(request, response);
        }

        else if ("verifyOtp".equals(action)) {

            String userOtp = request.getParameter("otp");
            session.setAttribute("enteredOtp", userOtp);

            String sessionOtp = (String) session.getAttribute("otp");

            if (sessionOtp != null && sessionOtp.equals(userOtp)) {

                session.setAttribute("otpVerified", true);

                request.setAttribute("success",
                        "OTP verified successfully");

            } else {
                request.setAttribute("error",
                        "Invalid OTP");
            }

            request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp")
                   .forward(request, response);
        }

        else if ("resetPassword".equals(action)) {

            Boolean verified =
                    (Boolean) session.getAttribute("otpVerified");

            if (verified != null && verified) {

                String email =
                        (String) session.getAttribute("email");

                String newPassword =
                        request.getParameter("password");

                boolean updated =
                        userDAO.updatePassword(email, newPassword);

                if (updated) {

                    session.removeAttribute("otp");
                    session.removeAttribute("otpVerified");
                    session.removeAttribute("enteredOtp");
                    session.removeAttribute("email");

                    request.setAttribute("success",
                            "Password reset successful");

                } else {
                    request.setAttribute("error",
                            "Failed to reset password");
                }

            } else {
                request.setAttribute("error",
                        "Please verify OTP first");
            }

            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}