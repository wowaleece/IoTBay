<%-- 
    Document   : SearchProducts
    Created on : 17/05/2021, 6:44:00 PM
    Author     : 61432
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </body>
</html>
