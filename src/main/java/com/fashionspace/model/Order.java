package com.fashionspace.model;

public class Order {

    private int orderId;
    private int userId;
    private String orderStatus;
    private String paymentMethod;
    private double totalAmount;
    private String imageUrl;

    public Order() {
    }

    public Order(
            int orderId,
            int userId,
            String orderStatus,
            String paymentMethod,
            double totalAmount,
            String imageUrl) {

        this.orderId = orderId;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.totalAmount=totalAmount;
        this.imageUrl=imageUrl;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}