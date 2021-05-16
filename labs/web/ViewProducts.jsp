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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

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
 
        <div>
            <h1 style="color:lightblue" >Products</h1> 
           
             <table border = "1" width = "100%">
                <thead>
                    <tr>
                        <th> Product name </th>
                        <th> Quantity </th>
                        <th> Stock Level </th>
                        <th> Price </th>
                        <th> Category </th> 
                        <th> Add to cart </th> 
                    </tr>
                </thead> 
                
                        <c:forEach items="${products}" var="product">
                        <tr>
                            <td>${product.productName}</td>
                            <td>${product.quantity}</td>
                            <td>${product.stockLevel}</td>
                            <td>${product.unitPrice}</td>
                            <td>${product.category}</td>
                            <td> 
                            <form action="AddToCartServlet" method="post" >
                                    <input class = "button" type="submit"  value="Add">
                                </form>
                            </td> 
                        </tr>  
                       </c:forEach>>
            </table> 
            
            <br>
            <br>
            
            <!-- TO DO: FIGURE OUT HOW TO DO AN IF STATEMENT --> 
            
            <p> Welcome ${user.email}</td></p>
            <p style="color:grey">Note* If you are not logged in:  </p>
            <p> Click <a class="p" href="login.jsp">here</a> to login.</p>
            <p> Click <a class="p" href="register.jsp"> here</a> to register.</p>
        </>
    </body>
</html>
