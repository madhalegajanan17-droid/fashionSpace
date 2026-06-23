<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Forgot Password</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/login.css">

<style>

.login-container{
    width:100%;
    min-height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
    background:#f5f5f5;
}

.login-box{
    width:400px;
    background:white;
    padding:30px;
    border-radius:10px;
    box-shadow:0px 0px 10px rgba(0,0,0,0.1);
}

.input-group{
    margin-bottom:20px;
}

.input-group label{
    display:block;
    margin-bottom:8px;
    font-weight:bold;
}

.input-group input{
    width:100%;
    height:45px;
    padding-left:10px;
    border:1px solid #ccc;
    border-radius:5px;
    font-size:16px;
}

.small-btn{
    width:130px;
    height:35px;
    border:none;
    background:#0d6efd;
    color:white;
    border-radius:5px;
    cursor:pointer;
    margin-top:10px;
    margin-bottom:20px;
}

.small-btn:hover{
    background:#0b5ed7;
}

.success{
    color:green;
    text-align:center;
    margin-bottom:15px;
}

.error{
    color:red;
    text-align:center;
    margin-bottom:15px;
}

</style>

</head>

<body>

<div class="login-container">

<div class="login-box">

<h1 style="text-align:center;">
Forgot Password
</h1>

<p style="text-align:center;">
Send OTP, verify OTP, then reset password
</p>

<%
String success =
(String)request.getAttribute("success");

String error =
(String)request.getAttribute("error");

String enteredEmail =
(String)session.getAttribute("email");

String enteredOtp =
(String)session.getAttribute("enteredOtp");

if(enteredEmail == null){
    enteredEmail = "";
}

if(enteredOtp == null){
    enteredOtp = "";
}
%>

<% if(success != null){ %>

<p class="success">
<%= success %>
</p>

<% } %>

<% if(error != null){ %>

<p class="error">
<%= error %>
</p>

<% } %>

<!-- SEND OTP -->

<form
action="${pageContext.request.contextPath}/forgot-password"
method="post">

<div class="input-group">

<label>Email</label>

<input
type="email"
name="email"
value="<%= enteredEmail %>"
placeholder="Enter email"
required>

</div>

<button
type="submit"
name="action"
value="sendOtp"
class="small-btn">

Send OTP

</button>

</form>

<!-- VERIFY OTP -->

<form
action="${pageContext.request.contextPath}/forgot-password"
method="post">

<div class="input-group">

<label>OTP</label>

<input
type="text"
name="otp"
value="<%= enteredOtp %>"
placeholder="Enter OTP"
required>

</div>

<button
type="submit"
name="action"
value="verifyOtp"
class="small-btn">

Verify OTP

</button>

</form>

<!-- RESET PASSWORD -->

<form
action="${pageContext.request.contextPath}/forgot-password"
method="post">

<div class="input-group">

<label>New Password</label>

<input
type="password"
name="password"
placeholder="Enter new password"
required>

</div>

<button
type="submit"
name="action"
value="resetPassword"
class="small-btn">

Reset Password

</button>

</form>

</div>

</div>

</body>

</html>