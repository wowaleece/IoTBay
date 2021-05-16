<%-- 
    Document   : index
    Created on : 3 Apr 2021, 11:05:02 pm
    Author     : marinasantanelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Home</title>
    </head>
    <body>
        
            <h1>IoTBay Home</h1>
 
     <p>Welcome to the IoTBay Web Application - Login or register to make a purchase or continue browsing!</p> 
         <br>
        <div class="button-container-div">
            <a class="button" href="register.jsp">Register </a>
            <a class="button" href="login.jsp">Log In </a>
            <a class="button" href="ViewProducts.jsp">Browse </a>
        </div>
        
        <!--<div>
            <a class="button" href="mainGuest.jsp">Guest Enter</a>
        </div> -->
        
        
        <jsp:include page="/ConnServlet" flush="true" /> <!-- loads db conn -->
        
        <%--
        <form action="register.jsp" method="POST">
            <input type="submit" value="Register">
        </form>
         <br  />
         <form action="login.jsp" method="POST">
            <input type="submit" value="Login">
        </form>
        --%>
        
        
    </body>
</html>
