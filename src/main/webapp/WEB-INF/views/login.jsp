<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FashionSpace Login</title>

<link rel="stylesheet"
href="<%=request.getContextPath()%>/assets/css/login.css">

</head>

<body>

<div class="login-page">

    <div class="login-container">

        <div class="login-card">

            <h2>Welcome Back</h2>

            <p class="subtitle">
                Login to continue shopping
            </p>

            <form action="<%=request.getContextPath()%>/login"
                  method="post">

                <div class="input-group">

                    <label>Email</label>

                    <input type="email"
                           name="email"
                           required>

                </div>

                <div class="input-group">

                    <label>Password</label>

                    <input type="password"
                           name="password"
                           required>

                </div>

                <button type="submit"
                        class="login-btn">

                    Login

                </button>

            </form>

            <div class="extra-links">

                <a href="<%=request.getContextPath()%>/forgot-password">

                    Forgot Password?

                </a>

            </div>

            <div class="register-text">

                Don't have an account?

                <a href="<%=request.getContextPath()%>/register">

                    Register

                </a>

            </div>

        </div>

    </div>

</div>

</body>
</html>