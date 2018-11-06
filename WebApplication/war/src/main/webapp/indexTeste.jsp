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
    <c:forEach items="${listaArtists}" var="artist">
        <tr>
            <td>${artist.getIdArtista()}</td>
            <td>${artist.getNomeArtista()}</td>
            <td>${artist.getIdArtistaLastfm()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaGenerosJogos}" var="gameGenre">
        <tr>
            <td>${gameGenre.getIdGeneroJogo()}</td>
            <td>${gameGenre.getNomeGenero()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaMusics}" var="music">
        <tr>
            <td>${music.getIdMusica()}</td>
            <td>${music.getTituloMusica()}</td>
            <td>${music.getIdMusicaLastfm()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaRelacoes}" var="musicReleaseAndGameMap">
        <tr>
            <td>${musicReleaseAndGameMap.getIdRelacao()}</td>
            <td>${musicReleaseAndGameMap.getIdGeneroJogo()}</td>
            <td>${musicReleaseAndGameMap.getIdMusica()}</td>
            <td>${musicReleaseAndGameMap.getIdAlbum()}</td>
            <td>${musicReleaseAndGameMap.getIdArtista()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${listaSystemUsers}" var="systemUser">
        <tr>
            <td>${systemUser.getIdUsuario()}</td>
            <td>${systemUser.getLogin()}</td>
            <td>${systemUser.getSenha()}</td>
            <td>${systemUser.getUsuarioSteam()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>