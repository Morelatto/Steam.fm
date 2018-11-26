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

public class Controller extends HttpServlet {

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
        gameGenreToMusicReleaseManager = serviceLocator.getMusicReleaseAndGameMapManager();
        recommendationManager = serviceLocator.getRecommendationManager();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     * @throws IOException
     *             if an I/O error occurs
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        HttpSession session = request.getSession();
        String steamID = (String) session.getAttribute("id");

        if (command == null) {
            throw new IOException("Null command");
        }

        switch (command) {
        case "login":
            login(request, response, session);
            break;
        case "recomendacao":
            recommendation(response, session, steamID);
            break;
        case "logout":
            logout(response, session);
            break;
        default:
            throw new IOException("Invalid command");
        }
    }

    private void logout(HttpServletResponse response, HttpSession session) throws IOException {
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    private void recommendation(HttpServletResponse response, HttpSession session, String steamID) throws IOException {
        List<MusicRelease> recommendations = new ArrayList<>();
        List<Game> gameList = gameManager.getGamesBySteamId(steamID);
        for (Game game : gameList) {
            for (GameGenre gameGenre : game.getGameGenreList()) {
                gameGenre = gameGenreManager.getByName(gameGenre.getName());
                if (gameGenre != null) {
                    GameGenreToMusicRelease gameGenreToMusicRelease = gameGenreToMusicReleaseManager
                            .getByGameGenre(gameGenre);
                    if (gameGenreToMusicRelease != null) {
                        recommendations.addAll(recommendationManager.getRecommendation(gameGenreToMusicRelease
                                .getMusicRelease()));
                    }
                }
            }
            game.setRecommendationList(recommendations);
        }
        session.setAttribute("gameList", gameList);
        response.sendRedirect("recomendacao.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        String steamUser;
        String steamID;
        switch (request.getParameter("commandAux")) {
        case "sistema":
            SteamFmUser steamFmUser = loginManager.authorize(request.getParameter("loginSistema"),
                    request.getParameter("senhaSistema"));
            if (steamFmUser != null) {
                session.setAttribute("user", steamFmUser);
                if (steamFmUser.getSteamUser() == null) {
                    session.setAttribute("admin", true);
                }
                response.sendRedirect("home.jsp");
            } else {
                session.setAttribute("usuarioInvalido", "sistema");
                response.sendRedirect("index.jsp");
            }
            break;
        case "steam":
            steamUser = request.getParameter("loginSteam");
            if (!"true".equals(request.getParameter("id"))) {
                steamID = loginManager.getSteamIdFromUsername(steamUser);
                if (steamID == null) {
                    session.setAttribute("usuarioInvalido", "steam");
                    response.sendRedirect("index.jsp");
                } else {
                    session.setAttribute("steamUser", steamUser);
                    session.setAttribute("steamID", steamID);
                    response.sendRedirect("home.jsp");
                }
            } else {
                session.setAttribute("steamID", steamUser);
                response.sendRedirect("home.jsp");
            }
            break;
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     * @throws IOException
     *             if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     * @throws IOException
     *             if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controller class";
    }

}
