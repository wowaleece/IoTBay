<%-- 
    Document   : index
    Created on : 3 Apr 2021, 11:05:02 pm
    Author     : marinasantanelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <%session.invalidate();%>
        <title>Home</title>
    </head>
    
    <body>


        
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="index.jsp">Home</a>
            <a href="register.jsp">Register</a>
            <a href="login.jsp"">Login</a>
            <a href="mainGuest.jsp"">Browse Products</a>
          </div>
       
        <span style="font-size:30px;cursor:pointer;color:#FFFFFF;" onclick="openNav()">&#9776; Menu </span>
    
        <script>
        function openNav() {
          document.getElementById("mySidenav").style.width = "250px";
        }

        function closeNav() {
          document.getElementById("mySidenav").style.width = "0";
        }
        
        </script>
            <h1>IoTBay Home</h1>
        <div>Welcome to the IoTBay Web Application!</div>
        <br  />
        
        <div>
            <a class="button" href="register.jsp">Register</a>
        </div>
        <br  />
        <div>
            <a class="button" href="login.jsp">Log In</a>
        </div>        
        <br  />
        <div>
            <a class="button" href="mainGuest.jsp">Guest Enter</a>
        </div>
        
        
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
