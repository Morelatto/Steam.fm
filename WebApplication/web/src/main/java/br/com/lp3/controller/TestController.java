package br.com.lp3.controller;

import br.com.lp3.rmi.dao.RemoteDAOOperations;
import br.com.lp3.utilities.ServiceLocator;
import br.com.lp3.entities.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestController extends HttpServlet {

    private HttpSession session;

    public TestController() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Test controller for database operations";
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession();
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        String command = request.getParameter("command");
        String type = request.getParameter("type");

        if (command != null && type != null) {
            if ("song".equals(type)) {
                Song song = Song
                        .builder()
                        .lastFmId("test")
                        .name("test")
                        .image("test")
                        .description("test")
                        .url("test")
                        .build();
                doOperation(serviceLocator.getSongManager().getOperations(), song, command);
            } else if ("album".equals(type)) {
                Album album = Album
                        .builder()
                        .lastFmId("test")
                        .name("test")
                        .image("test")
                        .description("test")
                        .url("test")
                        .build();
                doOperation(serviceLocator.getAlbumManager().getOperations(), album, command);
            } else if ("artist".equals(type)) {
                Artist artist = Artist
                        .builder()
                        .lastFmId("test")
                        .name("test")
                        .image("test")
                        .description("test")
                        .url("test")
                        .build();
                doOperation(serviceLocator.getArtistManager().getOperations(), artist, command);
            } else if ("gameGenre".equals(type)) {
                GameGenre gameGenre = GameGenre.builder().id(1L).name("test").build();
                doOperation(serviceLocator.getGameGenreManager().getOperations(), gameGenre, command);
            } else if ("gameGenreToMusicRelease".equals(type)) {
                GameGenreToMusicRelease gameGenreToMusicRelease = GameGenreToMusicRelease
                        .builder()
                        .id(1L)
                        .gameGenre(GameGenre.builder().build())
                        .song(Song.builder().build())
                        .album(Album.builder().build())
                        .artist(Artist.builder().build())
                        .build();
                doOperation(serviceLocator.getGameGenreToMusicReleaseManager().getOperations(),
                        gameGenreToMusicRelease,
                        command);
            } else if ("steamFmUser".equals(type)) {
                SteamFmUser steamFmUser = SteamFmUser
                        .builder()
                        .id(1L)
                        .login("test")
                        .password("test")
                        .steamUser("test")
                        .build();
                doOperation(serviceLocator.getSteamFmUser().getOperations(), steamFmUser, command);
            }
            response.sendRedirect("indexTeste.jsp");
        }
    }

    private <T> void doOperation(RemoteDAOOperations<T> operations, T t, String operation) {
        if ("create".equals(operation)) {
            operations.save(t);
        } else if ("read".equals(operation)) {
            session.setAttribute(t.getClass().getSimpleName() + "List", operations.getAll());
        } else if ("update".equals(operation)) {
            operations.update(t);
        } else if ("delete".equals(operation)) {
            operations.delete(t);
        }
    }

}
