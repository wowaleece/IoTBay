<%-- 
    Document   : main
    Created on : 7 Apr 2021, 9:26:26 pm
    Author     : marinasantanelli
--%>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Main page</title>
    </head>
    <body>
        <%
            User user = (User)session.getAttribute("user");
        %>
        <div>
            <h1 style="color:white">IoT Bay Store</h1>
            <a class="button" href="logout.jsp">Logout</a>
            <a class="button" href="account.jsp">View Account</a>
            
            <h1 style="color:white" >Products</h1>
            Product 1 <br> Product 2 <br> Product 3 
            <br>
            <br>
            <p style="color:white">You are logged in as ${user.fname} ${user.lname} </p>
        </div>
    </body>
</html>
