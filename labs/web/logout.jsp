<%-- 
    Document   : logout
    Created on : 05/04/2021, 1:21:59 AM
    Author     : alice_zly8mn7
--%>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Confirm Logout</title>
    </head>
    <body>
        <jsp:include flush="true" page="/LogoutServlet"/>
    </body>

</html>
