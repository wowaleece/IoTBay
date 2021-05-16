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
<%@page import="uts.isd.model.dao.DBManager"%>

<!DOCTYPE html>

<%  int orderId = (int) session.getAttribute("orderID");
    int userId = (int) session.getAttribute("userID");
    String UserID = userId.toString(); %> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Individual Order </title>
    </head>
    
    <% ArrayList<Orders> orders = (ArrayList<Orders>)session.getAttribute("orders");
        session.setAttribute("orders", orders);
        request.setAttribute("orders", orders); %>
        
    <body>
        <h1>Viewing Order History </h1>
        <table border="1">
            <thead>
                <tr>
                    <th class="p">Order Number</th>
                    <th class="p">Date Ordered</th>
                    <th class="p">Payment Status</th>
                    <th class="p">Order Status</th>
                </tr>
                <tr>
                <c:forEach items="${orders}" var="order">  
                    <tr>
                        <td>${Orders.OrderID}</td>
                        <td>${Orders.OrderDate}</td>
                        <td>${Orders.PaymentStatus}</td>
                        <td>${Orders.OrderStatus}</td>
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
                </tr>
            </thead>
        </table>
                    <br  />
                    <br  />
    </body>
</html>
