<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Steam.fm home</title>
</head>
<body>
<form action="Controller">
    <input type="hidden" name="command" value="recommendation"/>
    <input type="submit" value="Recommendation"/>
</form>
</body>
</html>