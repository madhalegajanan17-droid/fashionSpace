<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Register</title>

<link rel="stylesheet"
	href="<%= request.getContextPath() %>/assets/css/register.css">

</head>

<body>

<%@ include file="partials/header.jsp" %>
<%@ include file="partials/navbar.jsp" %>

<div class="register-page">

	<div class="register-box">

		<h1>Register</h1>

		<%
			String errorMessage =
				(String) request.getAttribute("errorMessage");

			if (errorMessage != null) {
		%>

		<p class="error-message">
			<%= errorMessage %>
		</p>

		<%
			}
		%>

		<form action="<%= request.getContextPath() %>/register"
			  method="post">

			<input type="text"
				   name="fullName"
				   placeholder="Full Name"
				   required>

			<input type="email"
				   name="email"
				   placeholder="Email"
				   required>

			<input type="text"
				   name="phone"
				   placeholder="Phone Number"
				   required>

			<input type="password"
				   name="password"
				   placeholder="Password"
				   required>

			<select name="gender" required>

				<option value="">
					Select Gender
				</option>

				<option value="Male">
					Male
				</option>

				<option value="Female">
					Female
				</option>

			</select>

			<textarea name="address"
					  placeholder="Address"
					  rows="4"
					  required></textarea>

			<button type="submit">
				Register
			</button>

		</form>

		<p class="register-link">

			Already have an account?

			<a href="<%= request.getContextPath() %>/login">
				Login
			</a>

		</p>

	</div>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>