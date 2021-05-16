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
    <table class="displayTable">
        <tr>
            <td><label name="email" id="email">Email:</label></td>   <td>${user.email}</td>
        </tr>
        <tr>
            <td><label name="email" id="email">uType:</label></td>   <td>${user.email}</td>
        </tr>
        <tr>
            <td><label name="email" id="email">phoneNo:</label></td>   <td>${user.email}</td>
        </tr>
    </table>
        
        <div>'
        </div>
    <form id="edit" action="/ProfileServlet/Admin/Edit" method="get"><input type="submit" form="edit" value="Edit"/></form>
</div> <!-- /container -->
</html>