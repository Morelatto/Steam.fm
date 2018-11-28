<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Steam.fm login</title>
    <script src="js/jquery-3.3.1.min.js"></script>
</head>
<body>

<c:if test="${invalidUser}">
    <script>alert('${invalidUserReason}');</script>
</c:if>

<select id="loginOptions" title="Login options">
    <option value="" selected="selected"></option>
    <option value="system_form">System login</option>
    <option value="app_form">App login</option>
</select>

<form action="Controller" name="system_form" id="system_form" style="display:none">
    <input type="text" placeholder="username" name="username" required/>
    <input type="password" placeholder="password" name="password" required/>
    <input type="hidden" name="command" value="login"/>
    <input type="hidden" name="loginType" value="system"/>
    <input type="submit" value="Login">
</form>

<form action="Controller" name="app_form" id="app_form" style="display:none">
    <input type="text" placeholder="steam username" name="steamUsername" required/>
    <input type="hidden" name="command" value="login"/>
    <input type="hidden" name="loginType" value="app"/>
    <input type="submit" value="Enter">
</form>

<script>
    $("#loginOptions").on("change", function () {
        $("#" + $(this).val()).show().siblings().hide();
    });
</script>
</body>
</html>