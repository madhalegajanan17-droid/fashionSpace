<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.fashionspace.model.Product" %>

<%@ include file="partials/header.jsp" %>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/assets/css/product-details.css?v=2">

<%@ include file="partials/navbar.jsp" %>

<%
Product product =
(Product) request.getAttribute("product");
%>

<div class="product-details-page">

<% if(product != null) { %>

    <div class="product-details-card">

        <div class="product-image-box">

            <img
            src="<%= request.getContextPath() %>/<%= product.getImageUrl() %>"
            alt="<%= product.getProductName() %>">

        </div>

        <div class="product-info-box">

            <h1>
                <%= product.getProductName() %>
            </h1>

            <p>
                Color :
                <%= product.getColor() %>
            </p>

            <p>
                Discount :
                <%= product.getDiscountPercent() %>% OFF
            </p>

            <p>
                Price :
                ₹ <%= product.getPrice() %>
            </p>

            <p>
                Product ID :
                <%= product.getProductId() %>
            </p>

            <p>
                Description :
                <%= product.getDescription() %>
            </p>

            <form action="<%= request.getContextPath() %>/cart"
                  method="post">

                <input type="hidden"
                       name="productId"
                       value="<%= product.getProductId() %>">

                <p>
                    Size :
                </p>

                <select class="size-dropdown"
                        name="sizeLabel"
                        required>

                    <option value="">
                        Select Size
                    </option>

                    <option value="M">
                        M
                    </option>

                    <option value="L">
                        L
                    </option>

                    <option value="XL">
                        XL
                    </option>

                </select>

                <p>
                    Quantity :
                </p>

                <select class="quantity-dropdown"
                        name="quantity"
                        required>

                    <option value="1">
                        1
                    </option>

                    <option value="2">
                        2
                    </option>

                    <option value="3">
                        3
                    </option>

                    <option value="4">
                        4
                    </option>

                    <option value="5">
                        5
                    </option>

                </select>

                <br>

                <button type="submit"
                        class="cart-btn">

                    Add To Cart

                </button>

            </form>

        </div>

    </div>

<% } else { %>

    <h2>
        Product not found
    </h2>

<% } %>

</div>

<%@ include file="partials/footer.jsp" %>