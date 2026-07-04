package com.fashionspace.util;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtility {

    private static final String FROM_EMAIL = System.getenv("MAIL_USERNAME");
    private static final String PASSWORD = System.getenv("MAIL_PASSWORD");
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "465";

    public static boolean sendOTP(String toEmail, String otp) {
        try {
            if (FROM_EMAIL == null || PASSWORD == null) {
                System.out.println("Email config missing. Skipping OTP email.");
                return false;
            }

            Properties properties = new Properties();
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.smtp.port", PORT);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("FashionSpace OTP Verification");
            message.setText("Your OTP is : " + otp);

            Transport.send(message);
            return true;

        } catch (Exception e) {
            System.out.println("OTP Email Error: " + e.getMessage());
            return false; // Won't crash your app
        }
    }

    public static boolean sendOrderSuccessEmail(String toEmail, int orderId, double totalAmount) {
        try {
            if (FROM_EMAIL == null || PASSWORD == null) {
                System.out.println("Email config missing. Skipping order email.");
                return false;
            }

            Properties properties = new Properties();
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.smtp.port", PORT);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("FashionSpace Order Placed Successfully");
            message.setText(
                "Hello Customer,\n\n" +
                "Your order has been placed successfully.\n\n" +
                "Order ID : " + orderId + "\n" +
                "Total Amount : ₹" + totalAmount + "\n\n" +
                "Thank you for shopping with FashionSpace."
            );

            Transport.send(message);
            return true;

        } catch (Exception e) {
            System.out.println("Order Email Error: " + e.getMessage());
            return false; // Won't crash your app
        }
    }
}