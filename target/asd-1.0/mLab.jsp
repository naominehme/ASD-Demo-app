<%-- 
    Document   : login
    Created on : 11/04/2018, 10:20:23 AM
    Author     : george
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="asd.demo.controller.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
        <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Nunito:400,300,700'>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/demo.css">
    </head>
    <%
        String status = request.getParameter("status");       
    %>
    <body style="background:none transparent;">

        <div class="row">
            <form class="login-form" action="ConnServlet" method="post" target="_parent">
                <p class="form_title">MLab Admin Login:  </p>&emsp;<span class="error"> <c:if test="${status!=null}"><c:out value="${status}"/></c:if></span>
                    <div class="form-group" id="username">
                        <input class="form-input" placeholder="Admin ID" name="adminemail" required="true">                                       
                    </div>
                    <div class="form-group" id="password">
                        <input type="password" class="form-input" name ="adminpassword" placeholder="Password" required="true">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Connect" class="login-button"/>                    
                    </div>
                </form>         
            </div>        
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js'></script>
        <script src='https://code.jquery.com/jquery-2.1.4.min.js'></script>       
    </body>

</html>
