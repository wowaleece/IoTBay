<%-- 
    Document   : viewCustomer
    Created on : 17 May 2021, 4:13:26 am
    Author     : super
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ProfileServlet" method="get"> 
            <table>
                <tr><td>Email Address:</td><td>${user.email}</td>   <td></td></tr>
                <tr><td>First Name:</td><td>${customer.fName}</td><td></td></tr>
                <tr><td>Last Name:</td><td>${customer.lName}</td>   <td></td></tr>
                <tr>
                    <td>Sex:</td>
                    <td>${customer.sex}</td>
                    <td></td>
                </tr>
                <tr><td>Date of Birth:</td><td>${customer.dob}</td><td></td></tr>
                <tr><td>Phone Number:</td><td>${user.phoneNo}" value="${user.phoneNo}</td><td></td></tr>
            </table>

            
            <table>
               <tr><td>Address</td></tr>
               <tr><td>Unit number:</td>    <td>${customer.address.unitNumber}</td></tr>
               <tr><td>Street Name:</td>    <td>${customer.address.streetName}</td></tr>
               <tr><td>Suburb:</td>         <td>${customer.address.suburb}</td></tr>
               <tr><td>Postcode:</td>     <td>${customer.address.postcode}</td></tr>
               <tr><td>State:</td>           <td>${customer.address.state}</td></tr>
               <tr><td>Country:</td>       <td>${customer.address.country}</td></tr>
           </table>
        
            
            <br>

            <div>
                <a class="button" href="account.jsp">Cancel</a>
                <input type="hidden" value="${customer.customerID}">
                <input class="button" type="submit" value="Edit">
            </div>
            <br>
            <br>
        </form> 
    </body>
</html>
