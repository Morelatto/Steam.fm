<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/logo.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<c:if test="${usuarioInvalido == 'sistema'}">
    <script>alert('Usuário Inválido.');</script>
</c:if>
<c:if test="${usuarioInvalido == 'steam'}">
    <script>alert('Usuário Não Encontrado.\nBusque Pelo STEAMID.');</script>
</c:if>
<div style="width:800px; height:350px; left:50%; top:50%; position:absolute; margin-left:-400px; margin-top:-290px;">
    <div class="board"><p id="error">steam</p>
        <p id="code">fm</p></div>
    <div style="position:relative; float:left;">
        <form action="Controller">
            <h2><span class="entypo-login"></span> Sistema</h2>
            <button class="submit"><span class="entypo-lock"></span></button>
            <span class="entypo-user inputUserIcon iconevelho"></span>
            <input type="text" class="user uservelho" placeholder="login" name="loginSistema" required/>
            <span class="entypo-key inputPassIcon"></span>
            <input type="password" class="pass" placeholder="senha" name="senhaSistema" required/>
            <input type="text" name="command" value="login" style="visibility: hidden;">
            <input type="text" name="commandAux" value="sistema" style="visibility: hidden;">
        </form>
    </div>
    <div style="position:relative; float:right;">
        <form action="Controller">
            <h2><span class="entypo-login"></span> Steam</h2>
            <button class="submit" style="height:56px;"><span class="entypo-lock"></span></button>
            <span class="entypo-user inputUserIcon iconenovo"></span>
            <input type="checkbox" class="check" name="steamID" value="true" title="STEAMID">
            <input type="text" class="user usernovo" style="" placeholder="usuário" name="loginSteam" required/>
            <input type="text" name="command" value="login" style="visibility: hidden;">
            <input type="text" name="commandAux" value="steam" style="visibility: hidden;">
        </form>
    </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="js/logo.js"></script>
<script src="js/index.js"></script>
</body>
</html>