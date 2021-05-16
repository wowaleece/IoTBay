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

        <form action="EditCServlet" method="POST">

            <table>
                <tr><td>Email Address:</td><td><input type="text" id="email" name="email" placeholder="${user.email}" required></td><td>${sessionScope.emailErr}${sessionScope.existErr}</td></tr>
                <tr><td>Password:</td><td><input type="password" id="password" name="password" required></td><td>${sessionScope.passErr}</td></tr>
                <tr><td>First Name:</td><td><input type="text" id="fname" name="fName" placeholder="${user.fName}" ></td><td></td></tr>
                <tr><td>Last Name:</td><td><input type="text" id="lname" name="lName" placeholder="${user.lName}" ></td><td></td></tr>
                <tr>
                    <td>Sex:</td>
                    <td>
                       <select id="sex" name="sex" placeholder="${user.sex}">
                           <option value="Other">Other</option>
                           <option value="Male">Male</option>
                           <option value="Female">Female</option>
                       </select>
                    </td>
                    <td></td>
                </tr>
                <tr><td>Date of Birth:</td><td><input type="date" id="dob" name="dob" placeholder="${user.dob}"></td><td></td></tr>
                <tr><td>Phone Number:</td><td><input type="text" id="phoneNo" name="phoneNo" placeholder="${user.phoneNo}"></td><td></td></tr>
            </table>
            <table>
               <tr><td>Address</td></tr>
               <tr><td><label type="number" for="unitNo">Unit number:</label></td>    <td><input type="text" id="unitNo" name="unitNo"></td></tr>
               <tr><td><label type="number" for="street">Street Name:</label></td>    <td><input type="text" id="street" name="street"></td></tr>
               <tr><td><label type="number" for="suburb">Suburb:</label></td>         <td><input type="text" id="suburb" name="suburb"></td></tr>
               <tr><td><label type="number" for="postcode">Postcode:</label></td>     <td><input type="number" id="postcode" name="postcode" maxlength="4"></td></tr>
               <tr><td><label type="number" for="state">State:</label></td>           <td><input type="text" id="state" name="state"></td></tr>
               <tr><td><label type="number" for="country">Country:</label></td>       <td><input type="text" id="country" name="country"></td></tr>
           </table>
            <br>

            <div>
                <a class="button" href="editCustomer.jsp">Cancel</a>
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
