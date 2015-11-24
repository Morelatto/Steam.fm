<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<html >
    <head>
        <title>${userSteam}</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/album.css">
        <!--<link rel="stylesheet" href="css/popup.css">-->
        <link rel="stylesheet" href="css/css.css">
    </head>
    <body>
        <link href='http://fonts.googleapis.com/css?family=Exo:700' rel='stylesheet' type='text/css'>
        <%@include file="header2.jspf" %>
        <div id="content-wrap">    
            <h1>${userSteam}</h1>
            <h2><span>${steamID}</span></h2>
            <c:set var="i" value="0"></c:set>
            <c:forEach items="${listaJogos}" var="jogo">
                <c:set var="i" value="${i+1}"></c:set>
                <c:set var="l" value="${jogo.getRecomendacoes().size()}"></c:set>
                <%--<c:if test="${l<1}">
                       <c:set var="size" value="20"></c:set>
                   </c:if>
                   <c:if test="${l>=1&&l<=5}">
                       <c:set var="size" value="210"></c:set>
                   </c:if>
                   <c:if test="${l>5&&l<=10}">
                       <c:set var="size" value="390"></c:set>
                   </c:if>
                   <c:if test="${l>10&&l<=15}">
                       <c:set var="size" value="580"></c:set>
                   </c:if> --%>
                <div class="row row-nav" data-height="${size}" id="row${i}" onmouseover="this.className += ' active';" onmouseout="this.className = 'row row-nav';">
                    ${jogo.getNome()}
                    <%@include file="albuns.jspf" %></div>
                </c:forEach>
            <div id="top">Top</div>
        </div>
        <script src="js/prefixfree.min.js"></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="js/index.js"></script>
        <script src="js/popup.js"></script>
        <!--<script> function popup() {}</script>-->
    </body>
</html>
