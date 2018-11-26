<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Database tests page</title>
</head>
<body>
<form action="TestController">
    <table>
        <tr>
            <td><input title="Song" type="radio" name="type" value="song"> Song</td>
            <td><input title="Album" type="radio" name="type" value="album"> Album</td>
            <td><input title="Artist" type="radio" name="type" value="artist"> Artist</td>
            <td><input title="GameGenre" type="radio" name="type" value="gameGenre"> GameGenre</td>
            <td><input title="GameGenreToMusicRelease" type="radio" name="type" value="gameGenreToMusicRelease"> GameGenreToMusicRelease</td>
            <td><input title="SteamFmUser" type="radio" name="type" value="steamFmUser"> SteamFmUser</td>
        </tr>
    </table>
    <table>
        <tr>
            <td><input type="submit" name="command" value="create"></td>
            <td><input type="submit" name="command" value="read"></td>
            <td><input type="submit" name="command" value="update"></td>
            <td><input type="submit" name="command" value="delete"></td>
        </tr>
    </table>
</form>

<hr>
<table border="1" style="width:100%">
    <c:forEach items="${SongList}" var="song">
        <tr>
            <td>${song.getLastFmId()}</td>
            <td>${song.getName()}</td>
            <td>${song.getImage()}</td>
            <td>${song.getDescription()}</td>
            <td>${song.getUrl()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${AlbumList}" var="album">
        <tr>
            <td>${album.getLastFmId()}</td>
            <td>${album.getName()}</td>
            <td>${album.getImage()}</td>
            <td>${album.getDescription()}</td>
            <td>${album.getUrl()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${ArtistList}" var="artist">
        <tr>
            <td>${artist.getLastFmId()}</td>
            <td>${artist.getName()}</td>
            <td>${artist.getImage()}</td>
            <td>${artist.getDescription()}</td>
            <td>${artist.getUrl()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${GameGenreList}" var="gameGenre">
        <tr>
            <td>${gameGenre.getId()}</td>
            <td>${gameGenre.getName()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${GameGenreToMusicReleaseList}" var="gameGenreToMusicRelease">
        <tr>
            <td>${gameGenreToMusicRelease.getId()}</td>
            <td>${gameGenreToMusicRelease.getGameGenre().getName()}</td>
            <td>${gameGenreToMusicRelease.getMusicRelease().getName()}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width:100%">
    <c:forEach items="${SteamFmUserList}" var="steamFmUser">
        <tr>
            <td>${steamFmUser.getId()}</td>
            <td>${steamFmUser.getLogin()}</td>
            <td>${steamFmUser.getPassword()}</td>
            <td>${steamFmUser.getSteamUser()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>