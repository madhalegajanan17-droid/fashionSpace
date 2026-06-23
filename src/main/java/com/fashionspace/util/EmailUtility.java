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

    private static final String FROM_EMAIL =
            "madhalegajanan2@gmail.com";

    private static final String PASSWORD =
            "vjfs vfad pkpi xktd";



    public static boolean sendOTP(
            String toEmail,
            String otp) {

        boolean status = false;

        try {

            Properties properties = new Properties();

            properties.put(
                    "mail.smtp.host",
                    "smtp.gmail.com"
            );

            properties.put(
                    "mail.smtp.port",
                    "587"
            );

            properties.put(
                    "mail.smtp.auth",
                    "true"
            );

            properties.put(
                    "mail.smtp.starttls.enable",
                    "true"
            );

            Session session = Session.getInstance(
                    properties,

                    new Authenticator() {

                        protected PasswordAuthentication getPasswordAuthentication() {

                            return new PasswordAuthentication(
                                    FROM_EMAIL,
                                    PASSWORD
                            );
                        }
                    }
            );

            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(FROM_EMAIL)
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );

            message.setSubject(
                    "FashionSpace OTP Verification"
            );

            message.setText(
                    "Your OTP is : " + otp
            );

            Transport.send(message);

            status = true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }



    public static boolean sendOrderSuccessEmail(
            String toEmail,
            int orderId,
            double totalAmount) {

        boolean status = false;

        try {

            Properties properties = new Properties();

            properties.put(
                    "mail.smtp.host",
                    "smtp.gmail.com"
            );

            properties.put(
                    "mail.smtp.port",
                    "587"
            );

            properties.put(
                    "mail.smtp.auth",
                    "true"
            );

            properties.put(
                    "mail.smtp.starttls.enable",
                    "true"
            );

            Session session = Session.getInstance(
                    properties,

                    new Authenticator() {

                        protected PasswordAuthentication getPasswordAuthentication() {

                            return new PasswordAuthentication(
                                    FROM_EMAIL,
                                    PASSWORD
                            );
                        }
                    }
            );

            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(FROM_EMAIL)
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );

            message.setSubject(
                    "FashionSpace Order Placed Successfully"
            );

            message.setText(

                    "Hello Customer,\n\n" +

                    "Your order has been placed successfully.\n\n" +

                    "Order ID : " + orderId + "\n" +

                    "Total Amount : ₹" + totalAmount + "\n\n" +

                    "Thank you for shopping with FashionSpace."
            );

            Transport.send(message);

            status = true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}