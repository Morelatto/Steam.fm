<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%-- <c:redirect url="indexTeste.jsp"></c:redirect> --%>
        Login Sistema
        <form action="Controller">
            <input type="text" name="command" value="login" hidden>
            <input type="text" name="commandAux" value="sistema" hidden>
            <input type="text" name="loginSistema" placeholder="loginSistema">
            <input type="password" name="senhaSistema" placeholder="senhaSistema">
            <input type="submit" value="OK">
        </form>

        <br>

        Login Steam
        <form action="Controller">
            <input type="text" name="command" value="login" hidden>
            <input type="text" name="commandAux" value="steam" hidden>
            <input type="text" name="loginSteam" placeholder="loginSteam" autofocus value="martvico">
            <input type="submit" value="OK">
        </form>

        <br>

        Login Steam ID
        <form action="Controller">
            <input type="text" name="command" value="login" hidden>
            <input type="text" name="commandAux" value="steamID" hidden>
            <input type="text" name="loginSteamID" placeholder="loginSteamID">
            <input type="submit" value="OK">
        </form>
    </body>
</html>