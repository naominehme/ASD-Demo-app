<%-- 
    Document   : welcome
    Created on : 23/03/2018, 3:13:21 PM
    Author     : george
--%>

<%@page import="asd.demo.model.dao.MongoDBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="asd.demo.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="animation.js"></script>
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <title>Welcome Page</title>
    </head>   
    <%
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User(name, email, password, phone);
        session.setAttribute("user", user);        
        
        String adminemail = (String)session.getAttribute("adminemail");
        String adminpass = (String)session.getAttribute("adminpassword");
        MongoDBConnector connector = new MongoDBConnector(adminemail, adminpass);
        if(connector != null ) connector.add(user); else out.print("Cannot add user");

    %>
    <body style="background:none transparent;"> 

        <p class="form_title">Your Information have been updated.</p>
        <table class="table">
            <tr><td>Name: </td><td class="text"><%=name%></td></tr>
            <tr><td>Email: </td><td class="text"><%=email%></td></tr>
            <tr><td>Password: </td><td class="text"><%= password%></td></tr>
            <tr><td>Phone: </td><td class="text"><%=phone%></td></tr>
        </table>       
    </body>
    <p class="p">Click <a href="main.jsp" class="link" target="_parent"> here </a> to go to main page.</p>

</html>
