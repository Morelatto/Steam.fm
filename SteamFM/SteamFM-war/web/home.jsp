<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${steamID}</h1>
        <h2>${userSteam}</h2>
        <hr>

        <form action="load.jsp" method="post">
            <input type="text" name="command" value="generosJogos" hidden>
            <input type="submit" value="Lista Gêneros Jogos">
        </form>

        <h2>Lista Gêneros Jogos</h2>
        <c:forEach items="${listaGenerosJogos}" var="generoJogo">
            ${generoJogo.getIdGeneroJogo()} - ${generoJogo.getNomeGenero()}<br>
        </c:forEach>

        <hr>

        <form action="load.jsp" method="post">
            <input type="text" name="command" value="jogos" hidden>
            <input type="submit" value="Lista Jogos">
        </form>

        <h2>Lista Jogos</h2>
        <c:forEach items="${listaJogos}" var="jogo">
            ${jogo.getSteamID()} - ${jogo.getNome()}<br>
            <ul>
                <c:forEach items="${jogo.getListaGeneroJogo()}" var="generoJogo">
                    <li>
                        ${generoJogo}
                    </li>
                </c:forEach>
            </ul>
        </c:forEach>

        <hr>

        <form action="load.jsp" method="post">
            <input type="text" name="command" value="recomendacao" hidden>
            <input type="submit" value="Lista Recomendacao">
        </form>

        <h2>Lista Músicas Recomendadas</h2>
        <c:forEach items="${listaMusicaRec}" var="musica">
            <c:out value="${musica.getTituloMusica()} - ${musica.getIdMusicaLastfm()}"></c:out><br>
        </c:forEach>

        <hr>

        <h2>Lista Álbuns Recomendados</h2>
        <c:forEach items="${listaAlbumRec}" var="album">
            <c:out value="${album.getTituloAlbum()} - ${album.getIdAlbumLastfm()}"></c:out><br>
        </c:forEach>

        <hr>

        <h2>Lista Artistas Recomendados</h2>
        <c:forEach items="${listaArtistaRec}" var="artista">
            <c:out value="${artista.getNomeArtista()} - ${artista.getIdArtistaLastfm()}"></c:out><br>
        </c:forEach>
    </body>
</html>
