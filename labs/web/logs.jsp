<%-- 
    Document   : welcome
    Created on : 3 Apr 2021, 10:27:38 pm
    Author     : marinasantanelli
--%>																 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Welcome Page</title>
    </head>
    <body>  
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
        <form action="UserLogs" method="post">
            <table border="0">
                <thead>
                    <tr>
                        <th><label name="tsFrom">From Date:${fromDateErr}</label></th>
                        <th><label name="tsTo">To Date:${toDateErr}</label></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input id="from" name="tsFrom" type="datetime-local" min="2021-05-16T00:00" value="${tsFrom}"></td>
                        <td><input id="from" name="tsTo" type="datetime-local" min="2021-05-16T00:00" value="${tsTo}"></td>
                    </tr>
                    <tr>
                        <td><input type="submit"></td>
                        <td>${existErr}</td>
                    </tr>
                </tbody>
            </table>

            
        </form>
        
        <table border="1">
            <thead>
                <tr>
                    <th class="p">TimeStamp</th>
                    <th class="p">Activity Type</th>
                    <th class="p">Activity Details</th>
                </tr>
            </thead>
            <tbody>
               
               
               <c:forEach items="${logs}" var="log">
                    <tr>
                    <td>${log.logTime}</td>
                    <td>${log.activityType}</td>
                    <td>${log.activityDetails}</td>

                    </tr>
               </c:forEach>
                
                
                
            </tbody>
        </table>
            
        


        
         <p style="color:#FFFFFF"> Click <a class="p" href="index.jsp">here</a> to proceed to the main index page.</p>
        
    </body>
  
</html>

