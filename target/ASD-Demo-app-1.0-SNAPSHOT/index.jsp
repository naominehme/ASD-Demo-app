<%@page import="asd.demo.model.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>        
        <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js" ></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" ></script>
        <script src="jsanimation.js"></script>
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <script type="text/javascript" src="js/script.js"></script>  
        <script type="text/javascript" src="js/animation.js"></script> 
        <link rel="stylesheet" type="text/css" href="css/style.css">  
        <link rel="stylesheet" type="text/css" href="css/led.css">
    </head>
    <title>Home</title>
    <%
        String status = (String)session.getAttribute("status");        
    %>
    <body onload="startTime()">           
        <div class="bs-docs-header" id="content" tabindex="-1">
            <span class="time" id="time" ></span>
            <div class="container">
                <h1>ASD  Demo Application</h1>                        
                <p class="p">Java Maven Web Application</p>                
            </div>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>

                        </button>
                        <a class="navbar-brand" href="#Register-Div" data-toggle="tab" id="Register-Tab">Register</a>
                        <a class="navbar-brand" href="#Login-Div" data-toggle="tab" id="Login-Tab">Login</a>
                        <a class="navbar-brand" href="#MLab-Div" data-toggle="tab" id="MLab-Tab">MLab Connect</a>
                        <div class="container">
                            <div class="led-box">
                                <div class="<%= (status != null) ? "led-green" : "led-red"%>"></div>                                
                            </div>                            
                        </div>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">                            
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Author<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="https://www.linkedin.com/in/georges-bou-ghantous/" target="_Blank" style="color:#cdbfe3;">Linked In</a></li>
                                    <li><a href="mailto:Georges.BouGhantous-1@uts.edu.au" style="color:#cdbfe3; ">Contact</a></li>
                                    <li><a href="https://www.uts.edu.au/" target="_Blank" style="color:#cdbfe3; ">Institution</a></li>
                                </ul>
                            </li> 
                        </ul>

                    </div> 
                </div>
            </nav>           
        </div> 
        <div class="container" style="width:102%; height: 75%;">
            <div class="row">                                  
                <div class="tab-content">
                    <iframe id="Display-frame" name="Display-frame" src="" frameborder="0"  allowFullScreen></iframe> 
                </div>                
            </div>            
        </div>        
        <div class="footer">            
            <h2 id="buttons-options" class="title">Control Panel</h2>
            <div class="bs-glyphicons" style="padding-bottom:10px;">          
                <a class="btn btn-default glyphicon glyphicon-refresh"  data-toggle="tooltip" title="Refresh" href="#" iclass="tooltip"id="Default-Tab"></a>             
                <a class="btn btn-primary glyphicon glyphicon-level-up" data-toggle="tooltip" title="GitHub" href="https://github.com/Georges034302" target="_blank"></a>               
                <a class="btn btn-info glyphicon glyphicon-cloud" data-toggle="tooltip" title="Google Drive" href="https://www.google.com/drive/" target="_blank"></a>            
                <a class="btn btn-warning glyphicon glyphicon-pencil" data-toggle="tooltip" title="Collaborate" href="http://collabedit.com/" target="_blank"></a>           
                <a class="btn btn-primary glyphicon glyphicon-send" data-toggle="tooltip" title="Email" href="mailto: "></a>
            </div>
        </div>  
        <script src="webjars/jquery/2.1.4/jquery.js"></script>
        <script src="webjars/bootstrap/3.3.5/js/bootstrap.js"></script>
    </body>
</html>