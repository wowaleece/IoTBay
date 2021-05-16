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
<%@page import="uts.isd.model.dao.DBManager"%>
<jsp:include page="header.jsp" />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Individual Order </title>
    </head>
    <body>
        <h1>Viewing Individual Order</h1>
        <table border="1">
            <thead>
                <tr>
                    <th class="p">Order Number</th>
                    <th class="p">Date Ordered</th>
                    <th class="p">Items</th>
                    <th class="p">Payment</th>
                    <th class="p">Status</th>
                </tr>
                <tr>
                    <%      try{
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
                                    <td><%String search_item_sql = "SELECT PRODUCTNAME FROM APP.ORDERLINEITEMS WHERE ORDERID = " + OrderID;
                                          resultSet_items = statement.executeQuery(search_item_sql);
                                          while(resultSet_items.next()){
                                              resultSet_items.getString("PRODUCTNAME")
                                          }%></td>
                                    <td><%=resultSet.getFloat("PAYMENTSTATUS"); %></td>
                                    <td><%=resultSet.getString("ORDERSTATUS"); %></td>
                                    </tr>
                                <%
                                }
                                connection.close();
                            } catch (Exception e) {
                                    e.printStackTrace();
                            }
                    %>
                </tr>
            </thead>
        </table>
                    <br  />
                    <br  />
    </body>
</html>
