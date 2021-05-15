<%-- 
    Document   : deleteProduct
    Created on : 16/05/2021, 1:06:29 AM
    Author     : Kayla Gelman
--%>

<%@page import="uts.isd.model.*"%> 
<%@page import="uts.isd.model.Product"%>
<%@page import="uts.isd.model.dao.DBManager"%>
<%@page import="uts.isd.model.dao.DBProduct"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>  
    <%
        Product product = (Product)request.getAttribute("product");
        int ProductID = Integer.parseInt(request.getParameter("ProductID"));

    %>
    
    <div style="text-align: center"> 
    <h1 style="padding-top: 30px"> Are you sure you want to delete? </h1>
    <div>       
       <form method="post" action="ProductsServletDelete">
           <input class = "button" type="submit"  value="Yes">
       </form>
        <br> 
       <div>
           <a class = "button" href="ProductsServletList" > No  </a>
       </div> 
    </div>
 </div>     
</html>


