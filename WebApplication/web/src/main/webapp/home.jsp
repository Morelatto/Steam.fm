<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/botao.css">
</head>
<body>
<div>
    <%@include file="WEB-INF/jspf/header.jspf" %>
    <%@include file="WEB-INF/jspf/menu.jspf" %>
    <div class="container-fluid" style="margin-top: 130px;">
        <div class="rand-covers row">
        </div>
    </div>
    <script id="cover" type="text/html">
        <div class="cover col-lg-2 col-sm-3 col-xs-3">
            <img src="{{src}}">
        </div>
    </script>
</div>
<div id="cta-buttons-wrapper">
    <a href="javascript:delay('${pageContext.servletContext.contextPath}/load.jsp?command=recommendation');">
        <div class="button">Recomendação</div>
    </a>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://mtcanvas.com/z/mustache.js'></script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
<script src="js/header.js"></script>
<script src="js/home.js"></script>
<script src="js/botao.js"></script>
<script src="js/botao2.js"></script>
<script src="js/logo.js"></script>
<script src="js/menu.js"></script>
</body>
</html>