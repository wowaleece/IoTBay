<%-- 
    Document   : newjsplogin
    Created on : 05/04/2021, 1:20:06 AM
    Author     : alice_zly8mn7
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Login Page</title>
    </head>
    <body>
        
            <form action="main.jsp" method="POST">
                <table>
                 <tr><td>Email Address:</td><td><input type="text" id="email" name="email" required="true"></td></tr>
                 <tr><td>Password</td><td><input type="password" id="password" name="password" required></td></tr>
                 </table>
             <br/>
             <div>
                 <a class="button" href="index.jsp">Cancel</a>
                 <input class="button" type="submit" value="Login">
             </div>              
            </form>

    </body>
</html>
