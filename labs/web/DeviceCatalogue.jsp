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
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Catalogue Management</title>
    </head>
    
        <h1>Device Catalogue</h1>
        <form method="post" action="ProductsServlet">
            <% 
                List<Product> products = (List<Product>)request.getAttribute("products");   
            %>
            <thead>
                <tr>
                    <th class="p"> Product ID</th>
                    <th class="p"> Product name </th>
                    <th class="p"> Stock Level </th>
                    <th class="p"> Price </th>
                    <th class="p"> Category </th>
                    <th class="p"> Actions </th>
                </tr>
            </thead> 

            <tbody> 
                <% for (Product result: products) { %>
                <tr>
                    <td><%=result.getID() %></td>
                    <td><%=result.getProductName() %></td>
                    <td><%=result.getStockLevel() %></td>
                    <td><%=result.getUnitPrice() %></td>
                    <td><%=result.getCategory() %></td>
                    <td><a class="button" href="EditProduct.jsp"> Edit </a> <a class="button" href="DeleteProduct.jsp"> Delete </a></td>
                </tr>
                <% } %> 
            </tbody>
        </form>
        
        <br> 
        <div>
            <a class="button" href="AddNewProduct.jsp">Add new device</a>
        </div>
        
        <br  />    
    

