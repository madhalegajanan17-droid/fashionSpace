<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password - FashionSpace</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .login-container {
            width: 100%;
            max-width: 420px;
            padding: 20px;
        }

        .login-box {
            background: #fff;
            padding: 40px 30px;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            animation: slideUp 0.5s ease;
        }

        @keyframes slideUp {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .login-box h1 {
            text-align: center;
            color: #333;
            margin-bottom: 10px;
            font-size: 28px;
            font-weight: 600;
        }

        .login-box p {
            text-align: center;
            color: #777;
            margin-bottom: 25px;
            font-size: 14px;
        }

        .input-group {
            margin-bottom: 20px;
        }

        .input-group label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: 500;
            font-size: 14px;
        }

        .input-group input {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 15px;
            transition: 0.3s;
            outline: none;
        }

        .input-group input:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        .small-btn {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #fff;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: 0.3s;
            margin-top: 10px;
        }

        .small-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .success {
            background: #d4edda;
            color: #155724;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 15px;
            text-align: center;
            border: 1px solid #c3e6cb;
        }

        .error {
            background: #f8d7da;
            color: #721c24;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 15px;
            text-align: center;
            border: 1px solid #f5c6cb;
        }

        hr {
            border: none;
            border-top: 1px solid #eee;
            margin: 25px 0;
        }
    </style>
</head>
<body>
<div class="login-container">
<div class="login-box">
<h1>Forgot Password</h1>
<p>Enter Email, then reset password</p>
<%
String success = (String)request.getAttribute("success");
String error = (String)request.getAttribute("error");
String enteredEmail = (String)session.getAttribute("email");

if(enteredEmail == null){ enteredEmail = ""; }
%>

<% if(success != null){ %>
<p class="success"><%= success %></p>
<% } %>

<% if(error != null){ %>
<p class="error"><%= error %></p>
<% } %>

<%-- STEP 1: SEND EMAIL --%>
<form action="${pageContext.request.contextPath}/forgot-password" method="post">
<div class="input-group">
<label>Email</label>
<input type="email" name="email" value="<%= enteredEmail %>" placeholder="Enter your registered email" required>
</div>
<button type="submit" name="action" value="sendOtp" class="small-btn">Submit Email</button>
</form>

<%-- STEP 2: RESET PASSWORD --%>
<% if(enteredEmail != null && !enteredEmail.equals("")){ %>
<hr>
<form action="${pageContext.request.contextPath}/forgot-password" method="post">
<div class="input-group">
<label>New Password</label>
<input type="password" name="password" placeholder="Enter new password" required>
</div>
<div class="input-group">
<label>Confirm Password</label>
<input type="password" name="confirmPassword" placeholder="Confirm new password" required>
</div>
<button type="submit" name="action" value="resetPassword" class="small-btn">Reset Password</button>
</form>
<% } %>

</div>
</div>
</body>
</html>