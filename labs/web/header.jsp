<%-- 
    Document   : header
    Created on : 13/05/2021, 11:45:29 AM
    Author     : 61432
--%>

<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/OrdersServlet" flush="true"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
        <style> 
             * {box-sizing: border-box;}
             tr:nth-child(even) {background-color: #f2f2f2;}
             tr:nth-child(odd) {background-color: #ffffff;}
        </style> 
    </head>
    <body>
        
        
         <%
            User user = (User)session.getAttribute("user");
        %>   
        
        <% if (user != null && user.isAdmin()) { %>
         <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a> 
            <p> Admin View </p> 
            <a href="AddNewProduct.jsp">Add New Product</a>
            <a href="ProductsServletAdminList">Device Management</a>
            <a href="logout.jsp">Logout</a>
          </div>

         <% } else if(user != null) {  %>
            <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="index.jsp">Home</a>
            <a href="logout.jsp">Logout</a>
            <a href="account.jsp">View Profile</a>
            <a href="ProductsServletList">Browse Products</a>
          </div>
         
           <% } else { %>
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <a href="index.jsp">Home</a>
                <a href="register.jsp">Register</a>
                <a href="login.jsp">Login</a>
            </div>
           <% } %>
        
        
        <div class="topnav"> 
            <div class="container-header">
            
            <span style="font-size:25px;cursor:pointer" onclick="openNav()">&#9776; Menu  </span>
    
            <script>
            function openNav() {
              document.getElementById("mySidenav").style.width = "250px";
            }

            function closeNav() {
              document.getElementById("mySidenav").style.width = "0";
            }
            </script>
        
            <nav class ="topmenu omega">
                <ul> 
                <li><a style = text-align: centre href="index.jsp"> IoTBay </a>  | </li> 
                <li> 
                    
                    <div class="wrap">
                        <div class="search">
                            <input type="text" class="searchTerm" placeholder="Search...">
                            <button type="submit" class="searchButton" onclick="location.href='ProductsServletSearchProducts'">
                              <i class="fa fa-search"></i>
                           </button>
                        </div>
                    </div>
                    
                </li> 
                <li>
                <!-- TO DO: ADD ACTUAL LINK TO VIEW CART -->
                <button onclick="location.href='cart.jsp'" type="button"><i style="font-size:24px" class="fa">  &#xf07a;</i></button></li>
                <li>
                <form method="POST" action="OrdersServletList">
                <input type="submit" value="Orders" />
                </form><i style="font-size:24px" class="fa"></i></button></li></ul> 
            </nav> 
            
          
           
            </div>
        </div>

        <!-- Add to cart --> 
        <!-- TO DO: make this a click-able button --> 
    </body>
</html>
