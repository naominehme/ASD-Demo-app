<%-- 
    Document   : logout
    Created on : 05/04/2018, 6:53:42 PM
    Author     : george
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="animation.js"></script>
        <script type="text/javascript" src="js/animation.js"></script>
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <title>Logout Page</title>
    </head>
    <body>       
        <% 
            session.invalidate();            
            response.sendRedirect("index.jsp");
        %>                
    </body>
</html>
