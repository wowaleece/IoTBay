<%-- 
    Document   : Account
    Created on : 05/04/2021, 1:17:41 AM
    Author     : alice_zly8mn7
--%>

<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>View Account</title>
    </head>
    <body>
    <body>
        <%--
        <%
            User user = (User)session.getAttribute("user");
        %>  --%>     
        <div>
            <p>You are viewing your account</p>
            <jsp:include flush="true" page="/ProfileServlet"/>
            <div class="errors">${sessionScope.existErr}</div>
            <a class="button" href="index.jsp">Go back</a>
            <a class="button" href="UserLogs">logs</a>
        </div>
    </body>
</html>