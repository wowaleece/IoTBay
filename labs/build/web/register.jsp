<%-- 
    Document   : register
    Created on : 3 Apr 2021, 10:14:49 pm
    Author     : marinasantanelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Sign Up</h1>

         <form action="welcome.jsp" onsubmit="if(document.getElementById('agree').checked) { return true; } else { alert('Please indicate that you have read and agree to the Terms of Service'); return false; }" method="POST">

             <table>
                 <tr><td>Email Address:</td><td><input type="text" id="email" name="email" required="true"></td></tr>
                 <tr><td>First Name:</td><td><input type="text" id="fname" name="fname" required></td></tr>
                 <tr><td>Last Name:</td><td><input type="text" id="lname" name="lname" required></td></tr>
                 <tr><td>Password</td><td><input type="password" id="password" name="password" required></td></tr>
                 <tr><td>Gender</td><td><input type="text" id="gender" name="gender" required></td></tr>
                 <tr><td>Date of Birth:</td><td><input type="date" id="dob" name="dob" required></td></tr>
                 <tr><td>Agree to TOS?</td>
                 <td><input type="checkbox" id="agree" name="inputGroup" value="Yes">I have read and agree to the Terms of Service.</td>
                 </tr>
             </table>
             <br  />

             <div>
                 <a class="button" href="index.jsp">Cancel</a>
                 <input class="button" type="submit" value="Sign up">
             </div>
                 
             
 </form>
  
<%--
<p>Agree to TOS?</p>
<input type="radio" id="yes" name="inputGroup" value="yes">
<label for="yes">Yes</label>
<input type="radio" id="no" name="inputGroup" value="no">
<label for="no">No</label>
         
<br  />
<br  />
<a href="index.jsp">Cancel</a>
<a href="welcome.jsp">Sign Up</a> 

<form action="welcome.jsp" method="POST">                       
<label for="email"></label><br>
<input type="text" id="email" name="email"><br>
<label for="name">First Name:</label><br>
<input type="text" id="fname" name="fname">
<br />
<label for="password">Password:</label><br>
<input type="password" id="password" name="password"><br>
<label for="gender">Gender:</label><br>
<input type="text" id="gender" name="gender">
<br />
<label for="favcol">Favourite Colour:</label><br>
<input type="text" id="favcol" name="favcol"><br>
<label for="tos">Agree to TOS:</label><br>
<input type="text" id="tos" name="tos">
<br />
<br />
<input type="submit" value="Register">
<div
</form> --%>
        
    </body>
</html>
