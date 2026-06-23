<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionspace.model.CartItem" %>

<%@ include file="partials/header.jsp" %>
<%@ include file="partials/navbar.jsp" %>

<div class="cart-page">

    <h1>My Cart</h1>

    <%
        List<CartItem> cartItems =
            (List<CartItem>) request.getAttribute("cartItems");

        double grandTotal = 0;

        if (cartItems != null && !cartItems.isEmpty()) {
    %>

    <table class="cart-table">

        <tr>
            <th>Product ID</th>
            <th>Size</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Total</th>
            <th>Action</th>
        </tr>

        <%
            for (CartItem item : cartItems) {

                double total =
                    item.getQuantity() * item.getUnitPrice();

                grandTotal += total;
        %>

        <tr>

            <td><%= item.getProductId() %></td>

            <td><%= item.getSizeLabel() %></td>

            <td><%= item.getQuantity() %></td>

            <td>₹<%= item.getUnitPrice() %></td>

            <td>₹<%= total %></td>

            <td>

                <a href="<%= request.getContextPath() %>/cart?action=remove&cartItemId=<%= item.getCartItemId() %>"
                   class="remove-btn">

                    Remove

                </a>

            </td>

        </tr>

        <%
            }
        %>

    </table>

    <div class="cart-summary">

        <h2>Grand Total: ₹<%= grandTotal %></h2>

        <a href="<%= request.getContextPath() %>/checkout"
           class="checkout-btn">

            Proceed to Checkout

        </a>

    </div>

    <%
        } else {
    %>

    <div class="empty-cart">

        <h2>Your cart is empty</h2>

        <a href="<%= request.getContextPath() %>/products"
           class="shop-btn">

            Continue Shopping

        </a>

    </div>

    <%
        }
    %>

</div>

<%@ include file="partials/footer.jsp" %>