<%-- 
    Document   : viewOrdersList
    Created on : 09/05/2021, 12:54:48 AM
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
<%@page import="uts.isd.model.Orders"%>
<%@page import="uts.isd.model.OrderLineItems"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>

<!DOCTYPE html>

<%  
    //int orderId = (Integer) session.getAttribute("OrderID");
    //int userId = (Integer) session.getAttribute("userID");
%> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order History </title>
    </head>
    
    <% 
        ArrayList <Orders> orders = (ArrayList<Orders>) session.getAttribute("Orders");
        session.setAttribute("Orders", orders);
//        request.setAttribute("Orders", orders); 
    %>

        
    <body>
        
        <h1>Viewing Order History </h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Order Number</th>
                    <th>Date Ordered</th>
                    <th>Payment Status</th>
                    <th>Order Status</th>
                    <th>Action</th>
                </tr>
            </thead>
                <c:forEach items="${orders}" var="Orders">  
                    ${orders}
                    <tr>
                        <td>${orders.OrderID}</td>
                        <td>${orders.OrderTime}</td>
                        <td>${orders.PaymentStatus}</td>
                        <td>${orders.Status}</td>
                        <td><form method="POST" action="OrdersServletDelete"><input type="button" value="Delete Order"></form><form method="post" action="OrdersServletFind" ><input type="button" value="View Order"></form></td>
                    </tr>
                </c:forEach>
                
      
                    <%--<%      try{
                                connection = DriverManager.getConnection(connectionUrl+database, userid, password);
                                statement=connection.createStatement();
    //                            UserID = session data extract user information query - help James. 
                                String sql = "SELECT * FROM APP.ORDERS WHERE USERID = '" + UserID +"'"; 
                                resultSet = statement.executeQuery(sql);
                                while(resultSet.next()){
                                %>
                                    <tr>
                                    <td><%=int OrderID = resultSet.getInt("ORDERID");%></td>
                                    <td><%=String OrderTime = (resultSet.getTimestamp("ORDERTIME")).toString; %></td>
                                    <td><%=resultSet.getFloat("PAYMENTSTATUS"); %></td>
                                    <td><%=resultSet.getString("ORDERSTATUS"); %></td>
                                    </tr>
                                <%
                                }
                                connection.close();
                            } catch (Exception e) {
                                    e.printStackTrace();
                            }
                    %>--%>
               
            
        </table>
                    <br  />
                    <br  />
    </body>
</html>
