<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Steam.fm recommendation</title>
    <link href="css/recommendations.css" rel="stylesheet">
</head>
<body>
<h1>${steamFmUser.getSteamUser().toUpperCase()}</h1>
<h2>${steamFmUser.getSteamId()}</h2>

<c:forEach items="${gameList}" var="game">
    <p> ${game.getId()} - ${game.getName()} </p>
    <%@ include file="WEB-INF/jspf/recommendations.jspf" %>
</c:forEach>

<form action="Controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="Logout"/>
</form>

</body>
</html>
