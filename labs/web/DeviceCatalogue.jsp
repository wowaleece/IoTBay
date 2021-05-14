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
        <form method="post" action="ProductsServlet">
            <table border = "1" width = "100%">
                <tr>
                    <th> Product name </th>
                    <th> Stock Level </th>
                    <th> Price </th>
                    <th> Category </th>
                    <th> Actions </th>
                </tr>

                <% 
                    List<Product> products = (List<Product>)request.getAttribute("product");   
                %>
                <% for (Product p: products) { %>
                    <tr>
                        <td><%=System.out.println(p.getID()) %></td>
                        <td><%=System.out.println(p.getProductName()) %></td>
                        <td><%=System.out.println(p.getStockLevel()) %></td>
                        <td><%=System.out.println(p.getUnitPrice()) %></td>
                        <td><%=System.out.println(p.getCategory()) %></td>
                        <td>
                            <a class="button" href="EditProduct.jsp"> Edit </a> 
                            <a class="button" href="DeleteProduct.jsp"> Delete </a>
                        </td>
                    </tr>
                    
                    <% } %> 
                
            </table> 
        </form>
    </body>
</html> 

