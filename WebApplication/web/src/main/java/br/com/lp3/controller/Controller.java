package br.com.lp3.controller;

import br.com.lp3.entities.dto.Game;
import br.com.lp3.rmi.manager.GameGenreManager;
import br.com.lp3.rmi.manager.GameManager;
import br.com.lp3.rmi.manager.LoginManager;
import br.com.lp3.rmi.manager.RecommendationManager;
import br.com.lp3.rmi.manager.GameGenreToMusicReleaseManager;
import br.com.lp3.utilities.ServiceLocator;
import br.com.lp3.entities.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO log all operations
public class Controller extends HttpServlet {

    private static final String SERVLET_DESCRIPTION = "Main controller for the Steam.fm app";

    private static final String HOME_PAGE = "home.jsp";
    private static final String INDEX_PAGE = "index.jsp";
    private static final String RECOMMENDATION_PAGE = "recomendacao.jsp";

    private static final String LOGIN_COMMAND = "login";
    private static final String RECOMMENDATION_COMMAND = "recommendation";
    private static final String LOGOUT_COMMAND = "logout";

    private static final String SYSTEM_LOGIN_TYPE = "system";
    private static final String APP_LOGIN_TYPE = "app";

    private static final String USERNAME_PARAMETER = "username";
    private static final String STEAM_USERNAME_PARAMETER = "steamUsername";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOGIN_TYPE_PARAMETER = "loginType";
    private static final String COMMAND_PARAMETER = "command";

    private static final String STEAM_ID_ATTRIBUTE = "steamId";
    private static final String INVALID_STEAM_USER_ATTRIBUTE = "invalidSteamUser";
    private static final String INVALID_SYSTEM_USER_ATTRIBUTE = "invalidSystemUser";
    private static final String USER_ATTRIBUTE = "user";
    private static final String GAME_LIST_ATTRIBUTE = "gameList";

    private HttpSession session;

    private LoginManager loginManager;
    private GameGenreManager gameGenreManager;
    private GameManager gameManager;
    private GameGenreToMusicReleaseManager gameGenreToMusicReleaseManager;
    private RecommendationManager recommendationManager;

    public Controller() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        loginManager = serviceLocator.getLoginManager();
        gameGenreManager = serviceLocator.getGameGenreManager();
        gameManager = serviceLocator.getGameManager();
        gameGenreToMusicReleaseManager = serviceLocator.getGameGenreToMusicReleaseManager();
        recommendationManager = serviceLocator.getRecommendationManager();
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

        String command = request.getParameter(COMMAND_PARAMETER);

        if (LOGIN_COMMAND.equals(command)) {
            String loginType = request.getParameter(LOGIN_TYPE_PARAMETER);
            boolean loginSuccessful;

            if (SYSTEM_LOGIN_TYPE.equals(loginType)) {
                loginSuccessful = login(request.getParameter(USERNAME_PARAMETER),
                        request.getParameter(PASSWORD_PARAMETER));
            } else if (APP_LOGIN_TYPE.equals(loginType)) {
                loginSuccessful = login(request.getParameter(STEAM_USERNAME_PARAMETER));
            } else {
                throw new IOException("Invalid login command");
            }

            if (loginSuccessful) {
                response.sendRedirect(HOME_PAGE);
            } else {
                response.sendRedirect(INDEX_PAGE);
            }
        } else if (RECOMMENDATION_COMMAND.equals(command)) {
            findRecommendations();
            response.sendRedirect(RECOMMENDATION_PAGE);
        } else if (LOGOUT_COMMAND.equals(command)) {
            session.invalidate();
            response.sendRedirect(INDEX_PAGE);
        } else {
            throw new IOException("Null command");
        }
    }

    private boolean login(String login, String password) {
        SteamFmUser steamFmUser = loginManager.authorize(login, password);
        if (steamFmUser != null) {
            session.setAttribute(USER_ATTRIBUTE, steamFmUser);
            // TODO change way to find out if user is admin
            if (steamFmUser.getSteamUser() == null) {
                session.setAttribute("admin", true);
            }
            return true;
        } else {
            session.setAttribute(INVALID_SYSTEM_USER_ATTRIBUTE, true);
            return false;
        }
    }

    private boolean login(String steamUsername) {
        String steamId = loginManager.getSteamIdFromUsername(steamUsername);
        if (steamId != null) {
            session.setAttribute(STEAM_ID_ATTRIBUTE, steamId);
            return true;
        } else {
            session.setAttribute(INVALID_STEAM_USER_ATTRIBUTE, true);
            return false;
        }
    }

    private void findRecommendations() {
        List<MusicRelease> recommendations = new ArrayList<>();
        List<Game> gameList = gameManager.getGamesBySteamId((String) session.getAttribute(STEAM_ID_ATTRIBUTE));
        gameList.forEach(game -> {
            game.getGameGenreList()
                    .stream()
                    .map(gameGenre -> gameGenreManager.getByName(gameGenre.getName()))
                    .filter(Objects::nonNull)
                    .map(gameGenre -> gameGenreToMusicReleaseManager.getByGameGenre(gameGenre))
                    .filter(Objects::nonNull)
                    .map(gameGenreToMusicRelease -> recommendationManager.getRecommendation(gameGenreToMusicRelease
                            .getMusicRelease()))
                    .forEach(recommendations::addAll);
            game.setRecommendationList(recommendations);
        });
        session.setAttribute(GAME_LIST_ATTRIBUTE, gameList);
    }

}
