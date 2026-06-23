<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.fashionspace.model.Order"%>

<%@ include file="partials/header.jsp"%>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/css/orders.css">

<%@ include file="partials/navbar.jsp"%>

<%
List<Order> orders =
        (List<Order>) request.getAttribute("orders");
%>

<div class="orders-page">

    <div class="orders-container">

        <h1>My Orders</h1>

        <%
        if (orders != null && !orders.isEmpty()) {

            for (Order order : orders) {
        %>

        <div class="order-card">

            <h3>
                Order ID :
                <%=order.getOrderId()%>
            </h3>

            <p>
                Status :
                <%=order.getOrderStatus()%>
            </p>

            <p>
                Payment :
                <%=order.getPaymentMethod()%>
            </p>

            <p>
                Total : ₹<%=order.getTotalAmount()%>
            </p>

            <a href="<%=request.getContextPath()%>/cancel-order?orderId=<%=order.getOrderId()%>"
               class="cancel-btn">
                Cancel Order
            </a>

        </div>

        <%
            }

        } else {
        %>

        <div class="order-card">

            <h3>No Orders Found</h3>

            <a href="<%=request.getContextPath()%>/products"
               class="shop-btn">
                Shop Now
            </a>

        </div>

        <%
        }
        %>

    </div>

</div>

<%@ include file="partials/footer.jsp"%>