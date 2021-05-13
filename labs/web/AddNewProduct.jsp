<%-- 
    Document   : AddNewProduct
    Created on : 07/05/2021, 4:39:16 PM
    Author     : Kayla Gelman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Catalogue Management</title>
    </head>
    <body>
        <h1>Add New Product</h1>
        
        <form action="AddProductsServlet" onsubmit="if(document.getElementById('agree').checked) { return true; } else { alert('Please confirm all details are correct'); return false; }" method="POST">

             <table>
                 <!-- TO DO  - ID should be automated --> 
                <!-- <tr><td>ID</td><td><input type="text" id="PRODUCTID" name="PRODUCTID" required="true"></td></tr>-->
                 <tr><td>Product Name</td><td><input type="text" id="PRODUCTNAME" name="PRODUCTNAME" required="true"></td></tr>
                 <!-- TO DO  - Make Stock level automated or add radio buttons for Low, Medium, High --> 
                 <td>Stock Level:</td>
                     <td>
                     <table>
                        <tr>
                           <td><input type="radio" id="Low" name="STOCKLEVEL" value="Low"></td>
                           <td><input type="radio" id="Medium" name="STOCKLEVEL" value="Medium"></td>
                           <td><input type="radio" id="High" name="STOCKLEVEL" value="High"></td>
                       </tr>
                       <tr>
                           <td><label class="radioLabel" for="Low">Low</label></td>
                           <td><label class="radioLabel" for="Medium">Medium</label></td>
                           <td><label class="radioLabel" for="High">High</label></td>
                       </tr>
                    </table>
                    </td>
                
                 <tr><td>Unit Price</td><td><input type="float" id="UnitPrice" name="UnitPrice" required></td></tr>
                 <!-- TO DO  - Make Category  a drop down list --> 
                 <tr><td>Category</td><td><input type="text" id="Category" name="Category" required></td></tr>
                 <tr><td>Are all product details correct? </td>
                 <td><input type="checkbox" id="agree" name="inputGroup" value="Yes"></td>
                 </tr>
             </table>
             <br  />

             <div>
                 <a class="button" href="index.jsp">Cancel</a>
                 <input class="button" type="submit" value="Enter">
             </div>
                 
             
 </form>
    </body>
</html>
