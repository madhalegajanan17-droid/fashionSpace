<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionspace.model.Product" %>
<%@ page import="com.fashionspace.model.Category" %>

<%@ include file="partials/header.jsp" %>
<%@ include file="partials/navbar.jsp" %>

<div class="products-page">

    <!-- SIDEBAR -->
    <aside class="filter-sidebar">

        <h2>Filters</h2>

        <form action="<%= request.getContextPath() %>/products" method="get">

            <input type="text"
                   name="search"
                   placeholder="Search products..."
                   class="search-input">

            <button type="submit" class="search-btn">
                Search
            </button>

        </form>

        <!-- CATEGORY SECTION -->

        <h3>Categories</h3>

        <a href="<%= request.getContextPath() %>/products"
           class="filter-link">
            All Products
        </a>

        <%

            List<Category> categories =
                    (List<Category>) request.getAttribute("categories");

            if (categories != null) {

                for (Category category : categories) {

        %>

        <a href="<%= request.getContextPath() %>/products?category=<%= category.getCategoryId() %>"
           class="filter-link">

            <%= category.getCategoryName() %>

        </a>

        <%

                }

            }

        %>

        <!-- SORT BY SECTION -->

        <div class="sort-section">

            <h3>Sort By</h3>

            <a href="<%= request.getContextPath() %>/products?sort=newest"
               class="sort-link">
                Newest Arrivals
            </a>

            <a href="<%= request.getContextPath() %>/products?sort=lowToHigh"
               class="sort-link">
                Price: Low to High
            </a>

            <a href="<%= request.getContextPath() %>/products?sort=highToLow"
               class="sort-link">
                Price: High to Low
            </a>

        </div>

    </aside>

    <!-- PRODUCTS SECTION -->

    <main class="products-content">

        <h1>Our Products</h1>

        <div class="products-grid">

        <%

            List<Product> products =
                    (List<Product>) request.getAttribute("products");

            if (products != null && !products.isEmpty()) {

                for (Product product : products) {

        %>

        <!-- PRODUCT CARD -->

        <div class="product-card">

            <img src="<%= request.getContextPath() %>/<%= product.getImageUrl() %>"
                 alt="<%= product.getProductName() %>">

            <h3><%= product.getProductName() %></h3>

            <p class="product-color">
                Color: <%= product.getColor() %>
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

        <p>No products found.</p>

        <%

            }

        %>

        </div>

    </main>

</div>

<%@ include file="partials/footer.jsp" %>