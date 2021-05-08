<%-- 
    Document   : mainGuest
    Created on : 08/04/2021, 8:10:07 PM
    Author     : alice_zly8mn7
--%>


<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Product"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="uts.isd.model.dao.DB"%>
<%@page import="uts.isd.model.dao.DBManager"%>
<%
String id = request.getParameter("PRODUCTID");
String driver = "org.apache.derby.jdbc.ClientDriver";
String connectionUrl = "jdbc:derby://localhost:1527/";
String database = "productsDB";
String userid = "isduser";
String password = "admin";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Guest</title>
    </head>
    <body>
        
        <h1 style="text-align:center">IoT Bay Store</h1>
        
        <!-- TO DO: QUERY DATABASE FOR ITEM NAME ---> 
        <div class="topnav">
            <div class="search-container">
                <form action="/action_page.php">
                  <input type="text" placeholder="Search..." name="search">
                  <button type="submit"><i class="fa fa-search"></i></button>
                </form> 
            </div>
        </div>
        
 
        <div>
            <h1 style="color:white" >Products</h1> 
           
            <table border = "1" width = "100%">
                <tr>    
                    <th class="p"> Product ID</th>
                    <th class="p"> Product name </th>
                    <th class="p"> Stock Level </th>
                    <th class="p"> Price </th>
                    <th class="p"> Category </th>
                </tr>
                <tr>
                    <%
                        try{
                        connection = DriverManager.getConnection(connectionUrl+database, userid, password);
                        statement=connection.createStatement();
                        String sql = "select * from PRODUCTS"; 
                        resultSet = statement.executeQuery(sql);
                        while(resultSet.next()){
                        %>
                            <tr>
                            <td><%=resultSet.getInt("PRODUCTID") %></td>
                            <td><%=resultSet.getString("PRODUCTNAME") %></td>
                            <td><%=resultSet.getString("STOCKLEVEL") %></td>
                            <td><%=resultSet.getFloat("UNITPRICE") %></td>
                            <td><%=resultSet.getString("CATEGORY") %></td>
                            </tr>
                        <%
                        }
                        connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %>
                 </tr>
                    
            </table>
            
            <br>
            <br>
            <p style="color:white">You are not logged in. </p>
            <p style="color:#FFFFFF"> Click <a class="p" href="login.jsp">here</a> to login.</p>
            <p style="color:#FFFFFF"> Click <a class="p" href="register.jsp"> here</a> to register.</p>
        </>
    </body>
</html>
