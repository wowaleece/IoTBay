<%-- 
    Document   : DeviceCatalogue
    Created on : 23/04/2021, 10:02:30 PM
    Author     : 61432
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Catalogue Management</title>
    </head>
    <body>
        <h1>Device Catalogue</h1>

        <br/>
       

        <table border="1">
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
                <tr>
                    <td>${product.ProductID}</td>
                    <td>${product.ProductName}</td>
                    <td>${product.StockLevel}</td>
                    <td>${product.UnitPrice}</td>
                    <td>${product.Category}</td>
                    <td><a class="button" href="EditProduct.jsp"> Edit </a> <a class="button" href="DeleteProduct.jsp"> Delete </a></td>
                </tr>
            </tbody>
        </table>
        
        <br> 
        <div>
            <a class="button" href="CreateDevice.jsp">Add new device</a>
        </div>
        
        <br  />    
    </body>
</html>
