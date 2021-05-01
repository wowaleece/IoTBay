<%-- 
    Document   : welcome
    Created on : 3 Apr 2021, 10:27:38 pm
    Author     : marinasantanelli
--%>
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
        <% 
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password"); 
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
        %>
        
        <%
            User user = new User(email, fname, lname, password, gender, dob);
            session.setAttribute("user", user);
        %>
        <h1>Welcome!</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th class="p"> Email</th>
                    <th class="p"> Name</th>
                    <th class="p"> Password</th>
                    <th class="p"> Gender</th>
                    <th class="p"> Date of Birth</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${user.email}</td>
                    <td>${user.fname} ${user.lname}</td>
                    <td>${user.password}</td>
                    <td>${user.gender}</td>
                    <td>${user.dob}</td>
                </tr>
            </tbody>
        </table>
                    <br  />
                    <br  />
      <%--
<ul>
         <li><p><b>Email:</b>
            <%= request.getParameter("email")%>
         </p></li>
         <li><p><b>First Name:</b>
            <%= request.getParameter("fname")%>
         </p></li>
         <li>
             <p><b>Password:</b>
            <%= request.getParameter("password")%>
         </p></li>
         <li><p><b>Gender:</b>
            <%= request.getParameter("gender")%>
         </p></li>
         <li><p><b>Favcol:</b>
            <%= request.getParameter("favcol")%>
         </p></li>
         <li><p><b>Tos:</b>
            <%= request.getParameter("tos")%>
         </p></li>
      </ul>
        --%>
        
         <p style="color:#FFFFFF"> Click <a class="p" href="main.jsp">here</a> to proceed to the main index page.</p>
        
    </body>
  
</html>

