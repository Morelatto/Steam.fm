<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>${userSteam}</title>
    <link rel="stylesheet" href="css/recomendacao.css">
    <link rel="stylesheet" href="css/album.css">
    <link rel="stylesheet" href="css/header.css">

    <link rel="stylesheet" href="css/css.css">
</head>
<body>
<link href='http://fonts.googleapis.com/css?family=Exo:700' rel='stylesheet' type='text/css'>
<%@include file="WEB-INF/jspf/header.jspf" %>
<%@include file="WEB-INF/jspf/menu.jspf" %>
<div id="content-wrap" style="margin-top: 150px;">
    <h1>${steamUser.toUpperCase()}</h1>
    <h2><span>${steamID}</span></h2>
    <c:set var="i" value="0"></c:set>
    <c:forEach items="${listaGames}" var="game">
        <c:set var="i" value="${i+1}"></c:set>
        <c:set var="l" value="${game.getListaRecomendacao().size()}"></c:set>
        <div class="row row-nav" id="row${i}" onmouseover="this.className += ' active';"
             onmouseout="this.className = 'row row-nav';">
                ${game.getNome()}
            <%@include file="WEB-INF/jspf/albuns.jspf" %>
        </div>
    </c:forEach>
    <div id="top">Top</div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="js/recomendacao.js"></script>
<script src="js/popup.js"></script>
<script src="js/header.js"></script>
<script src="js/logo.js"></script>
<script src="js/menu.js"></script>
</body>
</html>
