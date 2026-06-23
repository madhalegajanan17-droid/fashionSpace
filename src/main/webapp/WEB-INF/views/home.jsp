<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionspace.model.Product" %>

<%@ include file="partials/header.jsp" %>
<%@ include file="partials/navbar.jsp" %>

<link rel="stylesheet"
      href="<%= request.getContextPath() %>/assets/css/home.css">

<section class="hero-section">

    <div class="hero-content">

        <h1>Discover Your Style</h1>

        <p>
            Explore latest fashion collections for Men,
            Women, Kids and Accessories.
        </p>

        <a href="<%= request.getContextPath() %>/products"
           class="hero-btn">

            Shop Now

        </a>

    </div>

</section>

<section class="latest-section">

    <h2>Latest Products</h2>

    <div class="product-grid">

        <%
            List<Product> latestProducts =
                (List<Product>) request.getAttribute("latestProducts");

            if (latestProducts != null &&
                !latestProducts.isEmpty()) {

                for (Product product : latestProducts) {
        %>

        <div class="product-card">

            <img src="<%= request.getContextPath() %>/<%= product.getImageUrl() %>"
                 alt="<%= product.getProductName() %>">

            <h3>

                <%= product.getProductName() %>

            </h3>

            <p class="product-color">

                Color :
                <%= product.getColor() %>

            </p>

            <p class="discount">

                <%= product.getDiscountPercent() %>% OFF

            </p>

            <a href="<%= request.getContextPath() %>/product-details?id=<%= product.getProductId() %>"
               class="view-btn">

                View Details

            </a>

        </div>

        <%
                }

            } else {
        %>

        <p>No products available.</p>

        <%
            }
        %>

    </div>

</section>

<%@ include file="partials/footer.jsp" %>