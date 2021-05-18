<%-- 
    Document   : paymentConfirmation
    Created on : 16 May 2021, 1:26:44 pm
    Author     : marinasantanelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Successful payment notification</title>
    </head>
    
    <%
    Integer userId = (Integer) session.getAttribute("userId");
    %>
    
    
    <body>
        <body>
        ${userId}
        <p style="text-align: right; color: black; font-size:20px">
        <h1 style=color: black;>Payment ID#${paymentId} </h1>                                                     
        <h1>Your order has been confirmed!</h1>  

        <p>Thank you for purchasing from IOTBay, ${user.fname}</p>   
            <p> We will email you a copy of your receipt and delivery information.</p>
            <div class="button-container-div">
                <a class="button" href="ViewProducts.jsp">Keep Browsing</a>
                <a class="button" href="account.jsp">View Account</a>
                <a class="button" href="logout.jsp">Logout</a>
            </div>
    </body>
    </body>
</html>
