<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index - Teste</title>
</head>
<body>
<form action="ControllerTeste">
    <table border="1" style="width:30%">
        <tr>
            <td><input type="submit" name="command" value="albumC"></td>
            <td><input type="submit" name="command" value="albumR"></td>
            <td><input type="submit" name="command" value="albumU"></td>
            <td><input type="submit" name="command" value="albumD"></td>
        </tr>
        <tr>
            <td><input type="submit" name="command" value="artistaC"></td>
            <td><input type="submit" name="command" value="artistaR"></td>
            <td><input type="submit" name="command" value="artistaU"></td>
            <td><input type="submit" name="command" value="artistaD"></td>
        </tr>
        <tr>
            <td><input type="submit" name="command" value="generoJogoC"></td>
            <td><input type="submit" name="command" value="generoJogoR"></td>
            <td><input type="submit" name="command" value="generoJogoU"></td>
            <td><input type="submit" name="command" value="generoJogoD"></td>
        </tr>
        <tr>
            <td><input type="submit" name="command" value="musicaC"></td>
            <td><input type="submit" name="command" value="musicaR"></td>
            <td><input type="submit" name="command" value="musicaU"></td>
            <td><input type="submit" name="command" value="musicaD"></td>
        <tr>
            <td><input type="submit" name="command" value="relacaoC"></td>
            <td><input type="submit" name="command" value="relacaoR"></td>
            <td><input type="submit" name="command" value="relacaoU"></td>
            <td><input type="submit" name="command" value="relacaoD"></td>
        </tr>
        <tr>
            <td><input type="submit" name="command" value="usuarioC"></td>
            <td><input type="submit" name="command" value="usuarioR"></td>
            <td><input type="submit" name="command" value="usuarioU"></td>
            <td><input type="submit" name="command" value="usuarioD"></td>
        </tr>
    </table>
</form>

<hr>

<table border="1" style="width:100%">
    <c:forEach items="${listaAlbums}" var="album">
        <tr>
            <td>${album.getIdAlbum()}</td>
            <td>${album.getTituloAlbum()}</td>
            <td>${album.getIdAlbumLastfm()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaArtistas}" var="artista">
        <tr>
            <td>${artista.getIdArtista()}</td>
            <td>${artista.getNomeArtista()}</td>
            <td>${artista.getIdArtistaLastfm()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaGenerosJogos}" var="generoJogo">
        <tr>
            <td>${generoJogo.getIdGeneroJogo()}</td>
            <td>${generoJogo.getNomeGenero()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaMusicas}" var="musica">
        <tr>
            <td>${musica.getIdMusica()}</td>
            <td>${musica.getTituloMusica()}</td>
            <td>${musica.getIdMusicaLastfm()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaRelacoes}" var="relacao">
        <tr>
            <td>${relacao.getIdRelacao()}</td>
            <td>${relacao.getIdGeneroJogo()}</td>
            <td>${relacao.getIdMusica()}</td>
            <td>${relacao.getIdAlbum()}</td>
            <td>${relacao.getIdArtista()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaUsuarios}" var="usuario">
        <tr>
            <td>${usuario.getIdUsuario()}</td>
            <td>${usuario.getLogin()}</td>
            <td>${usuario.getSenha()}</td>
            <td>${usuario.getUsuarioSteam()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>