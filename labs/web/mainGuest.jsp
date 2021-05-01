<%-- 
    Document   : mainGuest
    Created on : 08/04/2021, 8:10:07 PM
    Author     : alice_zly8mn7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Product"%>
<%@page import="uts.isd.model.dao.DB"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="uts.isd.model.dao.DBManager" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Guest</title>
    </head>
    <body>
        <sql:setDataSource var = "snapshot" driver = "org.apache.derby.jdbc.ClientDriver"
         url = "jdbc:derby://localhost:1527/Users"
         user = "admin" password = "password"/>


        <div>
            <h1 style="color:white">IoT Bay Store</h1>
            
            <h1 style="color:white" >Products</h1>
            
            
            <table border = "1" width = "100%">
                <tr>
                    <th class="p"> Product ID</th>
                    <th class="p"> Product name </th>
                    <th class="p"> Stock Level </th>
                    <th class="p"> Price </th>
                    <th class="p"> Category </th>
                </tr>

                <c:forEach var = "row" items = "${result.rows}">
                   <tr>
                      <td><c:out value = "${row.ProductID}"/></td>
                      <td><c:out value = "${row.ProductName}"/></td>
                      <td><c:out value = "${row.StockLevel}"/></td>
                      <td><c:out value = "${row.UnitPrice}"/></td>
                      <td><c:out value = "${row.Category}"/></td>
                   </tr>
                </c:forEach>
            </table>
            
            <br>
            <br>
            <p style="color:white">You are not logged in. </p>
            <p style="color:#FFFFFF"> Click <a class="p" href="login.jsp">here</a> to login.</p>
            <p style="color:#FFFFFF"> Click <a class="p" href="register.jsp"> here</a> to register.</p>
        </div>
    </body>
</html>
