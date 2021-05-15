<%-- 
    Document   : DeviceCatalogueTBD
    Created on : 15/05/2021, 5:02:14 PM
    Author     : 61432
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
    
     <tbody> 
                    <% for (Product p: products) { %>
                    <tr>
                        <td><%=p.getProductName() %></td>
                        <td><%=p.getStockLevel() %></td>
                        <td><%=p.getUnitPrice() %></td>
                        <td><%=p.getCategory() %></td>
                        
                        <td>
                            <a class="button" href="EditProduct.jsp"> Edit </a> 
                            <a class="button" href="DeleteProduct.jsp"> Delete </a>
                        </td>
                    </tr>  
                <% } %> 
                </tbody>
                
                
                
                <tbody> 
                    <c:forEach items="${Product}" var="product" >
                     <tr>
                        <td><c:out value="${product.PRODUCTNAME}" /></td>
                        <td><c:out value="${product.QUANTITY}" /></td>
                        <td><c:out value="${product.STOCKLEVEL}" /></td>
                        <td><c:out value="${product.UNITPRICE}" /></td>
                        <td><c:out value="${product.CATEGORY}" /></td>
                        
                        <td>
                            <a class="button" href="EditProduct.jsp"> Edit </a> 
                            <a class="button" href="DeleteProduct.jsp"> Delete </a>
                        </td>
                     </tr>
                    </c:forEach>
                </tbody> 
</html>
