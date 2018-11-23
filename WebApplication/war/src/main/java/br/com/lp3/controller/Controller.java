package br.com.lp3.controller;

import br.com.lp3.entities.dto.Game;
import br.com.lp3.rmi.manager.GameGenreManager;
import br.com.lp3.rmi.manager.GameManager;
import br.com.lp3.rmi.manager.LoginManager;
import br.com.lp3.rmi.manager.RecommendationManager;
import br.com.lp3.rmi.manager.GameGenreToMusicReleaseManager;
import br.com.lp3.utilities.ServiceLocator;
import br.com.lp3.entities.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller extends HttpServlet {

    private LoginManager loginManager;
    private GameGenreManager generoJogoManager;
    private GameManager jogoManager;
    private GameGenreToMusicReleaseManager relacaoManager;
    private RecommendationManager recomendacaoManager;

    public Controller() throws NamingException {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        loginManager = serviceLocator.getLoginManager();
        generoJogoManager = serviceLocator.getGameGenreManager();
        jogoManager = serviceLocator.getGameManager();
        relacaoManager = serviceLocator.getMusicReleaseAndGameMapManager();
        recomendacaoManager = serviceLocator.getRecommendationManager();
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
        List<Game> gameList = jogoManager.getGamesBySteamId(steamID);
        List<GameGenre> gameGenreList = generoJogoManager.getGameGenres(gameList);
        List<GameGenreToMusicRelease> listaGameGenreToMusicRelease = relacaoManager.getListaRelacao(gameGenreList);
        for (Game game : gameList) {
            List<GameGenre> listaGameGenreObj = game.getGameGenreList();
            List<Song> listaRelacaoSong = new ArrayList<>();
            List<Artist> listaRelacaoArtist = new ArrayList<>();
            List<Album> listaRelacaoAlbum = new ArrayList<>();
            listaGameGenreObj
                    .forEach((GameGenre gameGenre) -> listaGameGenreToMusicRelease
                            .stream()
                            .filter((GameGenreToMusicRelease gameGenreToMusicRelease) -> Objects.equals(
                                    gameGenreToMusicRelease
                                    .getGameGenreId()
                                    .getId(),
                                    gameGenre.getId()))
                            .forEach(releaseAndGameMap -> processMap(listaRelacaoSong, listaRelacaoArtist, listaRelacaoAlbum, releaseAndGameMap)));
            List<MusicRelease> recomendacoes = new ArrayList<>();
            recomendacoes.addAll(recomendacaoManager.getMusicaRecomendacao(listaRelacaoSong));
            recomendacoes.addAll(recomendacaoManager.getArtistaRecomendacao(listaRelacaoArtist));
            recomendacoes.addAll(recomendacaoManager.getAlbumRecomendacao(listaRelacaoAlbum));
            game.setMusicReleaseRecommendationList(recomendacoes);
        }
        session.setAttribute("listaJogos", gameList);
        session.setAttribute("listaRelacao", listaGameGenreToMusicRelease);
        response.sendRedirect("recomendacao.jsp");
    }

    private void processMap(List<Song> listaRelacaoSong, List<Artist> listaRelacaoArtist,
            List<Album> listaRelacaoAlbum, GameGenreToMusicRelease releaseAndGameMap) {
        if (releaseAndGameMap.getSongId() != null) {
            listaRelacaoSong.add(releaseAndGameMap.getSongId());
        } else if (releaseAndGameMap.getArtistId() != null) {
            listaRelacaoArtist.add(releaseAndGameMap.getArtistId());
        } else {
            listaRelacaoAlbum.add(releaseAndGameMap.getAlbumId());
        }
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
                steamID = loginManager.getAnonSteamID(steamUser);
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
        return "Short description";
    }// </editor-fold>

}
