<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="partials/header.jsp" %>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/assets/css/orders.css">

<%@ include file="partials/navbar.jsp" %>

<%
Integer orderId =
    (Integer) request.getAttribute("orderId");
%>

<div class="orders-page">

    <div class="order-success-card">

        <h1>Order Placed Successfully</h1>

        <% if(orderId != null && orderId > 0) { %>

            <h3>Order ID: <%= orderId %></h3>

        <% } %>

        <p>Thank you for shopping with FashionSpace.</p>

        <a href="<%= request.getContextPath() %>/products"
           class="shop-btn">
            Continue Shopping
        </a>

    </div>

</div>

<%@ include file="partials/footer.jsp" %>