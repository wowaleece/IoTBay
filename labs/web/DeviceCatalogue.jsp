<%-- 
    Document   : DeviceCatalogue
    Created on : 23/04/2021, 10:02:30 PM
    Author     : Kayla Gelman
--%>

<%@page import="java.util.List"%>
<%@page import="java.lang.Iterable"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.*"%> 
<%@page import="uts.isd.model.Product"%>
<%@page import="uts.isd.model.dao.DBManager"%>
<%@page import="uts.isd.model.dao.DBProduct"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Device Catalogue</title>
    </head>
    <body>
        <h1>Device Catalogue</h1>
                
            <table border = "1" width = "100%">
                <thead>
                    <tr>
                        <th> Product name </th>
                        <th> Quantity </th>
                        <th> Stock Level </th>
                        <th> Price </th>
                        <th> Category </th>
                        <th> Actions </th>
                    </tr>
                </thead> 
                

                    <%-- List<Product> products = (List<Product>)request.getAttribute("products");
                        String show = (String) session.getAttribute("show");
                    for (Product prodcut: products) { --%>
                    
                        <c:forEach items="${products}" var="product">
                        <tr>
                            <td>${product.productName}</td>
                            <td>${product.quantity}</td>
                            <td>${product.stockLevel}</td>
                            <td>${product.unitPrice}</td>
                            <td>${product.category}</td>
                            <td>
                            <a class="button" href="EditProduct.jsp"> Edit </a> 
                                <%--<a class="button" href="DeleteProduct.jsp?ProductID=<%=p.getID()%>"> Delete </a> --%>
                                <form action="ProductsServletDelete" method="POST">
                                    <input type="hidden" name="productToDelete" value="${product.ID}">
                                    <input class="button" type="submit" value="Delete">
                                </form>
                            </td>
                        </tr>  
                        </c:forEach>
                        
                    
                        
                        
                        
                <%-- } --%> 
           
            </table> 
    </body> 
</html> 

