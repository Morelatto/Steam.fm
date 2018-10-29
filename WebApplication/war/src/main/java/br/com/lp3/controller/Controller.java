package br.com.lp3.controller;

import br.com.lp3.rmi.manager.GameGenreManager;
import br.com.lp3.rmi.manager.GameManager;
import br.com.lp3.rmi.manager.LoginManager;
import br.com.lp3.rmi.manager.RecommendationManager;
import br.com.lp3.rmi.manager.MusicReleaseAndGameMapManager;
import br.com.lp3.utilities.ServiceLocator;
import br.com.lp3.entities.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class Controller extends HttpServlet {

    private LoginManager loginManager;
    private GameGenreManager generoJogoManager;
    private GameManager jogoManager;
    private MusicReleaseAndGameMapManager relacaoManager;
    private RecommendationManager recomendacaoManager;

    private String command;
    private HttpSession session;
    private String steamID;
    private String userSteam;
    private boolean send;

    public Controller() throws NamingException {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        loginManager = serviceLocator.getLoginLocal();
        generoJogoManager = serviceLocator.getGeneroJogoLocal();
        jogoManager = serviceLocator.getJogoLocal();
        relacaoManager = serviceLocator.getRelacaoLocal();
        recomendacaoManager = serviceLocator.getRecomendacaoLocal();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        command = request.getParameter("command");
        session = request.getSession();
        steamID = (String) session.getAttribute("steamId");
        userSteam = (String) session.getAttribute("userSteam");
        send = "-1".equals(steamID) || "".equals(steamID);

        if (command != null) {
            switch (command) {
                case "login":
                    switch (request.getParameter("commandAux")) {
                        case "sistema":
                            User user = loginManager.authorize(request.getParameter("loginSistema"), request.getParameter("senhaSistema"));
                            if (user != null) {
                                session.setAttribute("user", user);
                                if (user.getSteanUser() == null) {
                                    session.setAttribute("admin", true);
                                }
                                response.sendRedirect("home.jsp");
                            } else {
                                session.setAttribute("usuarioInvalido", "sistema");
                                response.sendRedirect("index.jsp");
                            }
                            break;
                        case "steam":
                            userSteam = request.getParameter("loginSteam");
                            if (!"true".equals(request.getParameter("steamId"))) {
                                steamID = loginManager.getAnonSteamID(userSteam);
                                if (steamID == null) {
                                    session.setAttribute("usuarioInvalido", "steam");
                                    response.sendRedirect("index.jsp");
                                } else {
                                    session.setAttribute("userSteam", userSteam.toUpperCase());
                                    session.setAttribute("steamID", steamID);
                                    response.sendRedirect("home.jsp");
                                }
                            } else {
                                session.setAttribute("steamID", userSteam);
                                response.sendRedirect("home.jsp");
                            }
                            break;
                    }
                    break;
                case "recomendacao":
                    List<GameGenre> listaGenerosJogos = generoJogoManager.getGameGenresByUser(steamID);
                    List<Game> listaGames = jogoManager.getGamesByUser(steamID);
                    List<MusicReleaseAndGameMap> listaMusicReleaseAndGameMap = relacaoManager.getListaRelacao(listaGenerosJogos);
                    for (Iterator<Game> it = listaGames.iterator(); it.hasNext(); ) {
                        Game game = it.next();
                        List<GameGenre> listaGameGenreObj = generoJogoManager.getGameGenreListByGameGenreNames(game.getGameGenresNameList());
                        if (listaGameGenreObj.isEmpty()) {
                            it.remove();
                            continue;
                        }
                        List<Music> listaRelacaoMusic = new ArrayList<>();
                        List<Artist> listaRelacaoArtist = new ArrayList<>();
                        List<Album> listaRelacaoAlbum = new ArrayList<>();
                        listaGameGenreObj.stream().forEach((GameGenre gameGenre) -> {
                            listaMusicReleaseAndGameMap.stream().filter((MusicReleaseAndGameMap musicReleaseAndGameMap) -> Objects.equals(
                                    musicReleaseAndGameMap.getGameGenreId().getId(), gameGenre
                                    .getId())).forEach((relacao) -> {
                                if (relacao.getMusicId() != null) {
                                    listaRelacaoMusic.add(relacao.getMusicId());
                                } else if (relacao.getArtistId() != null) {
                                    listaRelacaoArtist.add(relacao.getArtistId());
                                } else {
                                    listaRelacaoAlbum.add(relacao.getAlbumId());
                                }
                            });
                        });
                        List<Object> recomendacoes = new ArrayList<>();
                        recomendacoes.addAll(recomendacaoManager.getMusicaRecomendacao(listaRelacaoMusic));
                        recomendacoes.addAll(recomendacaoManager.getArtistaRecomendacao(listaRelacaoArtist));
                        recomendacoes.addAll(recomendacaoManager.getAlbumRecomendacao(listaRelacaoAlbum));
                        game.setRecommendationList(recomendacoes);
                    }
                    session.setAttribute("listaJogos", listaGames);
                    session.setAttribute("listaRelacao", listaMusicReleaseAndGameMap);
                    response.sendRedirect("recomendacao.jsp");
                    break;
                case "logout":
                    session.invalidate();
                    response.sendRedirect("index.jsp");
                    break;
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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
