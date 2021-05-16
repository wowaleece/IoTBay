<%-- 
    Document   : addressSearch
    Created on : 16 May 2021, 12:30:20 am
    Author     : super
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
<div class="containter">
    <%--<table class="displayTable">
        <tHead>
            <tr>
        </tHead>
        <tbody>
        <c:forEach address="${addresses}" var="address">
            <tr>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table> --%>
    <%-- --%>
    <select name="address">
        <c:forEach items="${addresses}" var="address">
            <option value="${address.addressID}">${address}</option>
        </c:forEach>
    </select>
    <%-- --%>
    <%-- 
        <table border="1">
            <tr><td>AddressID</td><td>Steet Name</td></tr>
               <c:forEach items="${addresses}" var="address">
                    <tr>
                    <td>${address.addressID}</td>
                    <td>${address.streetName}</td>
                    </tr>
               </c:forEach>
                
            
        </table>--%>
        
    
    
</div> <!-- /container -->


</html>