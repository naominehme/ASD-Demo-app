<%-- 
    Document   : Register
    Created on : 11/04/2018, 10:20:23 AM
    Author     : george
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="asd.demo.controller.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Register</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
        <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Nunito:400,300,700'>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/demo.css">
    </head>
    <%
        String emailErr = request.getParameter("emailErr");
        String passErr = request.getParameter("passErr");
        String emptyErr = request.getParameter("empty");
    %>
    <body style="background:none transparent;">

        <div class="row">
            <form class="register-form" action="welcome.jsp" method="post">
                <p class="form_title">Register Details:  </p>&emsp;<span class="error"> <c:if test="${emptyErr!=null}"><c:out value="${emptyErr}"/></c:if></span>
                    <div class="form-group" id="fullname">
                        <input class="form-input" placeholder="Full name" name="name" required="true">&emsp;<span class="error"><c:if test="${emailErr!=null}"><c:out value="${emailErr}"/></c:if></span>                                       
                    </div>
                    <div class="form-group" id="username">
                        <input class="form-input" placeholder="Email" name="email" required="true">&emsp;<span class="error"><c:if test="${emailErr!=null}"><c:out value="${emailErr}"/></c:if></span>                                       
                    </div>
                    <div class="form-group" id="password">
                        <input type="password" class="form-input" name ="password" placeholder="Password" required="true">&emsp;<span class="error"><c:if test="${passErr!=null}"><c:out value="${passErr}"/></c:if></span>
                    </div>
                    <div class="form-group" id="phone">
                        <input class="form-input" placeholder="Phone" name="phone" required="true">&emsp;<span class="error"><c:if test="${emailErr!=null}"><c:out value="${emailErr}"/></c:if></span>                                       
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Register" class="login-button"/>                    
                    </div>
                </form>         
            </div>        
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js'></script>
        <script src='https://code.jquery.com/jquery-2.1.4.min.js'></script>       
    </body>

</html>
