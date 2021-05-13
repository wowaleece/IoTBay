<%-- 
    Document   : newjsplogin
    Created on : 05/04/2021, 1:20:06 AM
    Author     : alice_zly8mn7
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Login Page</title>
        
        <!-- 2 jQuery libraries (core and validation) from CDN websites for validating form data -->
        <!-- 
        <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
        <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
        
         -->
    </head>
    <body>

        <form action="LoginServlet" method="post"><!--<form action="main.jsp" method="POST"> -->
            <table>
             <tr><td>Email Address:</td><td><input type="text" id="email" name="email" required="true" placeholder="soandso@gmail.com"></td></tr>
             <tr><td>Password</td><td><input type="password" id="password" name="password" required size="30" ></td></tr>
             </table>
         <br/>
         <div>
             <a class="button" href="index.jsp">Cancel</a>
             <input class="button" type="submit" value="Login">
             <%= session.getAttribute("existErr")%>
             <%= session.getAttribute("emailErr")%>
             <%= session.getAttribute("passErr")%>
         </div>              
        </form>

    </body>
    
    <!-- js to validate login form before submission -->
    <!-- 
    <script type="text/javascript">
        $(document).ready(function() {
            $("#loginForm").validate({
                rules: {
                    email: {
                        required: true,
                        email: true
                    },

                    password: "required",
                },

                messages: {
                    email: {
                        required: "Please enter email",
                        email: "Please enter a valid email address"
                    },

                    password: "Please enter password"
                }
            });

        });
    </script>
     -->
</html>
