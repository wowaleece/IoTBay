<%-- 
    Document   : viewOrder
    Created on : 08/05/2021, 4:47:27 PM
    Author     : alice_zly8mn7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="uts.isd.model.dao.DB"%>
<%@page import="uts.isd.model.dao.DBManager_Orders"%>




<!DOCTYPE html>
<%--<%  int orderId = (int) session.getAttribute("orderID");
    int userId = (int) session.getAttribute("userID");
    String UserID = userId.toString(); %>--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Individual Order </title>
    </head>
    <body>
        <%--<jsp:include page="./isd/controller/OrdersServlet.java" flush="true"/>--%>
        <%--<jsp:include page ="./isd/controller/OrderLineItemsServlet" flush="true"/>--%>
        <%--<jsp:useBean id="order" type="java.util.ArrayList" scope="session"/>--%>
        
        <h1>Viewing Individual Order</h1>
        <table border="1">
            <thead>
                <tr>
                    <th class="p">Order Number</th>
                    <th class="p">Date Ordered</th>
                    <th class="p">Items</th>
                    <th class="p">Payment</th>
                    <th class="p">Status</th>
                    <th class="p">Action</th>
                </tr>
            </thead>
            <td>${Orders.OrderID}</td>
            <td>${Orders.OrderTime}</td>             
            <td>
                <c:forEach item = "${OrderLineItems}" var="OrderLines"> 
                    ${OrderLines.ProductName}
            </td>
            <td>${Orders.PaymentStatus}</td>
            <td>${Orders.OrderStatus}</td>
            <td><form method="POST" action="/OrdersServletDelete"><input type="button" value="Delete Order"></form></td>
            
                <tr>
                    
                </tr>
            
        </table>
                    <br/>
                    <br/>
    </body>
</html>
