package br.com.lp3.controller;

import br.com.lp3.utilities.RemoteDAOOperations;
import br.com.lp3.utilities.ServiceLocator;
import br.com.lp3.entities.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestController extends HttpServlet {

    private static final String TEST_STRING = "test";
    private static final String TEST_PAGE = "indexTest.jsp";
    private static final String COMMAND_REQUEST_PARAMETER_NAME = "command";
    private static final String TYPE_REQUEST_PARAMETER_NAME = "type";
    private static final String SONG_TYPE_REQUEST_PARAMETER = "song";
    private static final String ALBUM_TYPE_REQUEST_PARAMETER = "album";
    private static final String ARTIST_TYPE_REQUEST_PARAMETER = "artist";
    private static final String GAME_GENRE_TYPE_REQUEST_PARAMETER = "gameGenre";
    private static final String GAME_GENRE_TO_MUSIC_RELEASE_TYPE_REQUEST_PARAMETER = "gameGenreToMusicRelease";
    private static final String STEAM_FM_USER_TYPE_REQUEST_PARAMETER = "steamFmUser";
    private static final String CREATE_COMMAND_REQUEST_PARAMETER = "create";
    private static final String READ_COMMAND_REQUEST_PARAMETER = "read";
    private static final String UPDATE_COMMAND_REQUEST_PARAMETER = "update";
    private static final String DELETE_COMMAND_REQUEST_PARAMETER = "delete";
    private static final String SERVLET_DESCRIPTION = "Test controller for database operations";

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
        return SERVLET_DESCRIPTION;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession();
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        String command = request.getParameter(COMMAND_REQUEST_PARAMETER_NAME);
        String type = request.getParameter(TYPE_REQUEST_PARAMETER_NAME);

        if (command != null && type != null) {
            if (SONG_TYPE_REQUEST_PARAMETER.equals(type)) {
                doOperation(serviceLocator.getSongManager().getOperations(), buildSong(), command);
            } else if (ALBUM_TYPE_REQUEST_PARAMETER.equals(type)) {
                doOperation(serviceLocator.getAlbumManager().getOperations(), buildAlbum(), command);
            } else if (ARTIST_TYPE_REQUEST_PARAMETER.equals(type)) {
                doOperation(serviceLocator.getArtistManager().getOperations(), buildArtist(), command);
            } else if (GAME_GENRE_TYPE_REQUEST_PARAMETER.equals(type)) {
                doOperation(serviceLocator.getGameGenreManager().getOperations(), buildGameGenre(), command);
            } else if (GAME_GENRE_TO_MUSIC_RELEASE_TYPE_REQUEST_PARAMETER.equals(type)) {
                doOperation(serviceLocator.getGameGenreToMusicReleaseManager().getOperations(),
                        buildGameGenreToMusicRelease(),
                        command);
            } else if (STEAM_FM_USER_TYPE_REQUEST_PARAMETER.equals(type)) {
                doOperation(serviceLocator.getSteamFmUserManager().getOperations(), buildSteamFmUser(), command);
            }
            response.sendRedirect(TEST_PAGE);
        }
    }

    private Song buildSong() {
        return Song
                .builder()
                .lastFmId(TEST_STRING)
                .name(TEST_STRING)
                .image(TEST_STRING)
                .description(TEST_STRING)
                .url(TEST_STRING)
                .build();
    }

    private Album buildAlbum() {
        return Album
                .builder()
                .lastFmId(TEST_STRING)
                .name(TEST_STRING)
                .image(TEST_STRING)
                .description(TEST_STRING)
                .url(TEST_STRING)
                .build();
    }

    private Artist buildArtist() {
        return Artist
                .builder()
                .lastFmId(TEST_STRING)
                .name(TEST_STRING)
                .image(TEST_STRING)
                .description(TEST_STRING)
                .url(TEST_STRING)
                .build();
    }

    private GameGenre buildGameGenre() {
        return GameGenre.builder().id(1L).name(TEST_STRING).build();
    }

    private GameGenreToMusicRelease buildGameGenreToMusicRelease() {
        return GameGenreToMusicRelease
                .builder()
                .id(1L)
                .gameGenre(buildGameGenre())
                .song(buildSong())
                .album(buildAlbum())
                .artist(buildArtist())
                .build();
    }

    private SteamFmUser buildSteamFmUser() {
        return SteamFmUser.builder().id(1L).login(TEST_STRING).password(TEST_STRING).steamUser(TEST_STRING).build();
    }

    private <T> void doOperation(RemoteDAOOperations<T> operations, T t, String operation) {
        if (CREATE_COMMAND_REQUEST_PARAMETER.equals(operation)) {
            operations.save(t);
        } else if (READ_COMMAND_REQUEST_PARAMETER.equals(operation)) {
            session.setAttribute(t.getClass().getSimpleName() + "List", operations.getAll());
        } else if (UPDATE_COMMAND_REQUEST_PARAMETER.equals(operation)) {
            operations.update(t);
        } else if (DELETE_COMMAND_REQUEST_PARAMETER.equals(operation)) {
            operations.delete(t);
        }
    }

}
