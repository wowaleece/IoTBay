<%-- 
    Document   : paymentDetails
    Created on : 14 May 2021, 5:26:19 pm
    Author     : marinasantanelli
--%>

<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Payment Details</title>
    </head>
    <body>
        
        <form action="NewPaymentServlet" method="post">
       
        <h1> Order #${orderId} </h1>
        <h2> Total Price A$${order.getAmount()} </h2>
        
        <div class="body-text"> 
            <h3><label for name="paymentMethod">Payment Method</label></h3>
                <select id="paymentMethod" name="paymentMethod">
                <option value="Credit Card">Credit Card</option></select></div>
 
            <b><label for name="paymentDate">Payment Date</label></b>
                <input type="date" id="coloumn-left" name="paymentDate" placeholder="Payment Date" required/> 
             
                <div class="form-container">
                <div class="personal-information">
                <h1>Please enter your payment details</h1></div> 
            
             <input type="number" id="input-field" name="cardNumber" placeholder="Card Number" required/> 
             <input type="text" id="input-field" name="cardExpiry" placeholder="MM/YY" required/> 
             <input type="number" id="input-field" name="cvv" placeholder="CVV" required/> 
             <input type="text" id="input-field" name="nameOnCard" placeholder="Name on Card" required/> 
             
             <input type="submit" value="Proceed to Checkout">
        </form>
       
            <%-- <p><a class= "button" style="center" href="Payment1_OrderCart.jsp">Cancel</a></p> --%>
                
        </div>
       
    </body>
</html>
