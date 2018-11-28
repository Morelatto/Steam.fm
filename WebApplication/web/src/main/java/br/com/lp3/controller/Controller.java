package br.com.lp3.controller;

import br.com.lp3.entities.dto.Game;
import br.com.lp3.ejb.GameGenreManager;
import br.com.lp3.ejb.GameManager;
import br.com.lp3.ejb.LoginManager;
import br.com.lp3.ejb.RecommendationManager;
import br.com.lp3.ejb.GameGenreToMusicReleaseManager;
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

    private static final String RECOMMENDATION_COMMAND = "recommendation";
    private static final String LOGOUT_COMMAND = "logout";
    private static final String LOGIN_COMMAND = "login";
    private static final String LOGIN_SYSTEM_TYPE = "system";
    private static final String LOGIN_APP_TYPE = "app";

    private static final String USERNAME_PARAMETER = "username";
    private static final String STEAM_USERNAME_PARAMETER = "steamUsername";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOGIN_TYPE_PARAMETER = "loginType";
    private static final String COMMAND_PARAMETER = "command";

    private static final String INVALID_USER_ATTRIBUTE = "invalidUser";
    private static final String INVALID_USER_REASON_ATTRIBUTE = "invalidUserReason";
    private static final String STEAM_FM_USER_ATTRIBUTE = "steamFmUser";
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
        session = request.getSession();
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return SERVLET_DESCRIPTION;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter(COMMAND_PARAMETER);

        if (LOGIN_COMMAND.equals(command)) {
            String loginType = request.getParameter(LOGIN_TYPE_PARAMETER);
            boolean loginSuccessful = false;

            if (LOGIN_SYSTEM_TYPE.equals(loginType)) {
                loginSuccessful = login(request.getParameter(USERNAME_PARAMETER),
                        request.getParameter(PASSWORD_PARAMETER));
            } else if (LOGIN_APP_TYPE.equals(loginType)) {
                loginSuccessful = login(request.getParameter(STEAM_USERNAME_PARAMETER));
            }

            if (loginSuccessful) {
                response.sendRedirect(HOME_PAGE);
            }
        } else if (RECOMMENDATION_COMMAND.equals(command)) {
            findRecommendations((SteamFmUser) session.getAttribute(STEAM_FM_USER_ATTRIBUTE));
            response.sendRedirect(RECOMMENDATION_PAGE);
        } else if (LOGOUT_COMMAND.equals(command)) {
            session.invalidate();
        }

        response.sendRedirect(INDEX_PAGE);
    }

    private boolean login(String login, String password) {
        SteamFmUser steamFmUser = loginManager.authorize(login, password);
        return validateSteamFmUser(steamFmUser);
    }

    private boolean login(String steamUsername) {
        SteamFmUser steamFmUser = loginManager.register(steamUsername);
        return validateSteamFmUser(steamFmUser);
    }

    private boolean validateSteamFmUser(SteamFmUser steamFmUser) {
        if (steamFmUser == null) {
            session.setAttribute(INVALID_USER_ATTRIBUTE, true);
            session.setAttribute(INVALID_USER_REASON_ATTRIBUTE, "User not found on database");
            return false;
        } else if (steamFmUser.getSteamId() == null) {
            session.setAttribute(INVALID_USER_ATTRIBUTE, true);
            session.setAttribute(INVALID_USER_REASON_ATTRIBUTE, "Steam id not found for user");
            return false;
        }
        // TODO async get steam games
        session.setAttribute(STEAM_FM_USER_ATTRIBUTE, steamFmUser);
        return true;
    }

    private void findRecommendations(SteamFmUser steamFmUser) {
        List<MusicRelease> recommendations = new ArrayList<>();
        List<Game> gameList = gameManager.getGamesBySteamId(steamFmUser.getSteamId());
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
