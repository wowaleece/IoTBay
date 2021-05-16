<%-- 
    Document   : orderConfirmation
    Created on : 8 May 2021, 2:57:47 pm
    Author     : marinasantanelli
--%>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Order Confirmation</title>
    </head>

     <body>

        <%
            Integer paymentId = (Integer) session.getAttribute("paymentId");
        %>
        
        <p> userid #${userId}</p>
        <div class='center'>
            
        <p style="text-align: right; color: black; font-size:20px"> <i> Today's Date : <script> document.write(new Date().toLocaleDateString()); </script> </i></p>
        <h2 style="text-align: left"> Order #${orderId} </h2>
        <h2 style="font-size:30px; text-align:center ;">Confirm Purchase ?</h2>
        
        <form action = "paymentConfirmation.jsp" method="post">  
            

            <h3 style=' text-align: center'>Total Price A$${order.getAmount()} </h3>
            <div class="center"><table style="text-align: center">
           
            <p style=' text-align: center'> Payment Method  = ${payment.getPaymentMethod()} </p>
            <p style=' text-align: center'>    Payment Date    = ${payment.getPaymentDate()} </p>
            <p style=' text-align: center'>   Card Number   = ${payment.getCardNumber()} </p>
            <p style=' text-align: center'>   Card Expiry Date   = ${payment.getCardExpiry()} </p>
            <p style=' text-align: center'>       CVV       = ${payment.getCvv()} </p>
            <p style=' text-align: center'>  Name On Card   = ${payment.getNameOnCard()}</p>
    
            
            <p>If your payment details are correct, please confirm your purchase to complete the transaction:</p>
            <input class= button type="submit" value="Confirm purchase" >
             
        </form>
            
            
            <%-- 
            <p> <a class ="buttonpaycon" id="column-left" href="Payment_DeleteServlet?paymentId=<%=paymentId%>">Cancel</a></p>
                    
            <p> <a class= "buttonpaycon" id="column-right" href="Payment_EditServlet?paymentId=<%=paymentId%>">Edit</a> </p>
            </div>        
            --%>
    </body>
    
    
    
    
    
    
</html>