<%
    String path = request.getContextPath();

    Integer userId =
        (Integer) session.getAttribute("userId");

    String userName =
        (String) session.getAttribute("userName");
%>

<nav class="navbar">

    <div class="logo">
        <a href="<%= path %>/home">
            FashionSpace
        </a>
    </div>

    <div class="nav-links">

        <a href="<%= path %>/home">
            Home
        </a>

        <a href="<%= path %>/products">
            Products
        </a>

        <a href="<%= path %>/products?category=1">
            Men
        </a>

        <a href="<%= path %>/products?category=2">
            Women
        </a>

        <a href="<%= path %>/products?category=5">
            Accessories
        </a>

    </div>

    <div class="nav-icons">

        <!-- SEARCH FORM -->

        <form action="<%= path %>/products"
              method="get"
              class="search-form">

            <input type="text"
                   name="search"
                   placeholder="Search...">

            <button type="submit">
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>

        </form>

        <!-- CART ICON -->

        <a href="<%= path %>/cart">
            <i class="fa-solid fa-cart-shopping"></i>
        </a>

        <!-- ORDERS ICON -->

        <a href="<%= path %>/orders">
            <i class="fa-solid fa-bag-shopping"></i>
        </a>

<%
    if (userId != null) {
%>

        <span class="user-name">
            Hi, <%= userName %>
        </span>

        <a href="<%= path %>/logout"
           class="logout-btn">
            Logout
        </a>

<%
    } else {
%>

        <a href="<%= path %>/login">
            <i class="fa-regular fa-user"></i>
        </a>

<%
    }
%>

    </div>

</nav>