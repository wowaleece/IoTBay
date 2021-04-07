<%-- 
    Document   : display
    Created on : 5 Apr 2021, 8:36:48 pm
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
        Email : <jsp:getProperty name="user" property="email"/>
        <br  />
        Name : <jsp:getProperty name="user" property="fname"/>
    </body>
</html>
