<%-- 
    Document   : register
    Created on : 3 Apr 2021, 10:14:49 pm
    Author     : marinasantanelli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSS.css">
        <title>edit profile Page</title>
    </head>
    <body>
        <h1>Sign Up</h1>

        
        <form action="ProfileServlet" method="post">
            <table>
                <tr><td>Email Address:</td><td>${user.email}</td><td>${sessionScope.emailErr}${sessionScope.existErr}</td></tr>
                <tr><td>PassWord:</td><td><input type="password" id="password" name="password" placeholder="*******"></td><td></td></tr>
                <tr><td>First Name:</td><td><input type="text" id="fname" name="fName" placeholder="${customer.fName}" value="${customer.fName}"></td><td></td></tr>
                <tr><td>Last Name:</td><td><input type="text" id="lname" name="lName" placeholder="${customer.lName}"  value="${customer.lName}"></td><td></td></tr>
                <tr>
                    <td>Sex:</td>
                    <td>
                       <select id="sex" name="sex" placeholder="${customer.sex}" value="${customer.sex}">
                           <option value="Other">Other</option>
                           <option value="Male">Male</option>
                           <option value="Female">Female</option>
                       </select>
                    </td>
                    <td></td>
                </tr>
                <tr><td>Date of Birth:</td><td><input type="date" id="dob" name="dob" placeholder="${customer.dob}" value="${customer.dob}"></td><td></td></tr>
                <tr><td>Phone Number:</td><td><input type="text" id="phoneNo" name="phoneNo" placeholder="${user.phoneNo}" value="${user.phoneNo}"></td><td></td></tr>
            </table>

            
            <table>
               <tr><td>Address</td></tr>
               <tr><td><label type="number" for="unitNo">Unit number:</label></td>    <td><input type="text" id="unitNo" name="unitNo" value="${customer.address.unitNo}"></td></tr>
               <tr><td><label type="number" for="street">Street Name:</label></td>    <td><input type="text" id="street" name="street" value="${customer.address.street}"></td></tr>
               <tr><td><label type="number" for="suburb">Suburb:</label></td>         <td><input type="text" id="suburb" name="suburb" value="${customer.address.suburb}"></td></tr>
               <tr><td><label type="number" for="postcode">Postcode:</label></td>     <td><input type="number" id="postcode" name="postcode" maxlength="4" value="${customer.address.postcode}"></td></tr>
               <tr><td><label type="number" for="state">State:</label></td>           <td><input type="text" id="state" name="state" value="${customer.address.state}"></td></tr>
               <tr><td><label type="number" for="country">Country:</label></td>       <td><input type="text" id="country" name="country" value="${customer.address.country}"></td></tr>
           </table>
        
            
            <br>

            <div>
                <a class="button" href="account.jsp">Cancel</a>
                <input class="button" type="submit" value="Save">
            </div>
        </form>
            <%--<jsp:param name="addresses" value="${addresses}"/> --%>
       <%-- </jsp:include> --%>

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
