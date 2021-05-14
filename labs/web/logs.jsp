<%-- 
    Document   : welcome
    Created on : 3 Apr 2021, 10:27:38 pm
    Author     : marinasantanelli
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Log"%>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Welcome Page</title>
    </head>
    <body>
        
        <h1>Welcome!</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th class="p"> Email</th>
                    <!--<th class="p"> Name</th>-->
                    <%--<th class="p"> Password</th>--%>
                    <th class="p"> User Type</th>
                    <th class="p"> Phone No</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${user.email}</td>
                    <%--<td>${user.fname} ${user.lname}</td> --%>
                    <%--<td>${user.password}</td> --%>
                    <td>${user.uType}</td>
                    <td>${user.phoneNo}</td>
                </tr>
            </tbody>
        </table>
        <br>
        <br>
        
        <form action="UserLogsServlet">
            <table border="1">
            <thead>
                <tr>
                    <th class="p">TimeStamp</th>
                    <th class="p">Activity Type</th>
                    <th class="p">Activity Details</th>
                </tr>
            </thead>
            <tbody>
                <%
                ArrayList<Log> logs = (ArrayList<Log>)request.getAttribute("logs");   
                for (Log result: logs) { 
                %>
                <tr>
                    <td><%=result.getLogTime() %></td>
                    <td><%=result.getActivityType() %></td>
                    <td><%=result.getActivityDetails() %></td>
                    <td><a class="button" href="EditProduct.jsp"> Edit </a> <a class="button" href="DeleteProduct.jsp"> Delete </a></td>
                </tr>
                <% } %> 
            </tbody>
        </table>
            
        </form>


        
         <p style="color:#FFFFFF"> Click <a class="p" href="index.jsp">here</a> to proceed to the main index page.</p>
        
    </body>
  
</html>

