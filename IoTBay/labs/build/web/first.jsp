<%-- 
    Document   : first
    Created on : 5 Apr 2021, 8:35:07 pm
    Author     : marinasantanelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="user" scope="request" class="beans.UserBean"/>
        <jsp:setProperty name="user" property="*"/>
        <jsp:forward page="display.jsp"/>
    </body>
</html>
