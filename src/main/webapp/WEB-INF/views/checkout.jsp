<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionspace.model.CartItem" %>

<%@ include file="partials/header.jsp" %>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/assets/css/checkout.css">

<%@ include file="partials/navbar.jsp" %>

<div class="checkout-page">

    <h1>Checkout</h1>

    <div class="checkout-container">

        <!-- LEFT SIDE -->
        <div class="checkout-address">

            <h2>Delivery Address</h2>

            <form action="<%= request.getContextPath() %>/place-order"
                  method="post">

                <label>Full Name</label>

                <input type="text"
                       name="fullName"
                       required>

                <label>Phone Number</label>

                <input type="text"
                       name="phone"
                       required>

                <label>Address</label>

                <textarea name="address"
                          required></textarea>

                <!-- PAYMENT METHOD -->

                <label>Payment Method</label>

                <select name="paymentMethod"
                        id="paymentMethod"
                        required>

                    <option value="COD">
                        Cash On Delivery
                    </option>

                    <option value="UPI">
                        UPI
                    </option>

                    <option value="CARD">
                        Card
                    </option>

                </select>

                <!-- UPI BOX -->

                <div id="upiBox"
                     class="payment-box"
                     style="display:none;">

                    <label>UPI ID</label>

                    <input type="text"
                           name="upiId"
                           placeholder="example@upi">

                </div>

                <!-- CARD BOX -->

                <div id="cardBox"
                     class="payment-box"
                     style="display:none;">

                    <label>Card Number</label>

                    <input type="text"
                           name="cardNumber"
                           placeholder="Enter Card Number">

                    <label>Expiry Date</label>

                    <input type="text"
                           name="expiry"
                           placeholder="MM/YY">

                    <label>CVV</label>

                    <input type="password"
                           name="cvv"
                           placeholder="CVV">

                </div>

                <button type="submit"
                        class="place-order-btn">

                    Place Order

                </button>

            </form>

        </div>

        <!-- RIGHT SIDE -->

        <div class="checkout-summary">

            <h2>Order Summary</h2>

            <%

                List<CartItem> cartItems =
                    (List<CartItem>) request.getAttribute("cartItems");

                Double grandTotal =
                    (Double) request.getAttribute("grandTotal");

                if (cartItems != null && !cartItems.isEmpty()) {

                    for (CartItem item : cartItems) {

            %>

                <div class="summary-item">

                    <p>
                        Product ID :
                        <%= item.getProductId() %>
                    </p>

                    <p>
                        Size :
                        <%= item.getSizeLabel() %>
                    </p>

                    <p>
                        Quantity :
                        <%= item.getQuantity() %>
                    </p>

                    <p>
                        Price :
                        ₹<%= item.getUnitPrice() %>
                    </p>

                </div>

            <%

                    }

                } else {

            %>

                <p>No cart items found.</p>

            <%

                }

            %>

            <h3>

                Grand Total :

                ₹<%= grandTotal != null ? grandTotal : 0.0 %>

            </h3>

        </div>

    </div>

</div>

<!-- PAYMENT SCRIPT -->

<script>

window.onload = function () {

    const paymentMethod =
        document.getElementById("paymentMethod");

    const upiBox =
        document.getElementById("upiBox");

    const cardBox =
        document.getElementById("cardBox");

    paymentMethod.addEventListener(
        "change",
        function () {

            upiBox.style.display = "none";
            cardBox.style.display = "none";

            if (this.value === "UPI") {

                upiBox.style.display = "block";

            }

            else if (this.value === "CARD") {

                cardBox.style.display = "block";

            }

        }
    );

};

</script>

<%@ include file="partials/footer.jsp" %>