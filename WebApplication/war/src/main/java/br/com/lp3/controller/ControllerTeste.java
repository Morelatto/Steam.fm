package br.com.lp3.controller;

import br.com.lp3.rmi.manager.AlbumManager;
import br.com.lp3.rmi.manager.ArtistManager;
import br.com.lp3.rmi.manager.GameGenreManager;
import br.com.lp3.rmi.manager.MusicManager;
import br.com.lp3.rmi.manager.MusicReleaseAndGameMapManager;
import br.com.lp3.rmi.manager.UserManager;
import br.com.lp3.utilities.ServiceLocator;
import br.com.lp3.entities.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class ControllerTeste extends HttpServlet {

    private AlbumManager albumManager;
    private ArtistManager artistaManager;
    private GameGenreManager generoJogoManager;
    private MusicManager musicaManager;
    private MusicReleaseAndGameMapManager relacaoManager;
    private UserManager usuarioManager;

    private Album album;
    private Artist artist;
    private GameGenre gameGenre;
    private Music music;
    private MusicReleaseAndGameMap musicReleaseAndGameMap;
    private User user;

    private String command;
    private HttpSession session;

    public ControllerTeste() throws NamingException {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        generoJogoManager = serviceLocator.getGeneroJogoLocal();
        relacaoManager = serviceLocator.getRelacaoLocal();
        albumManager = serviceLocator.getAlbumLocal();
        artistaManager = serviceLocator.getArtistaLocal();
        musicaManager = serviceLocator.getMusicaLocal();
        usuarioManager = serviceLocator.getUsuarioLocal();
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

        if (command != null) {
            switch (command) {
                case "albumC":
                    album = new Album();
                    album.setId(100);
                    album.setLastFmId("teste");
                    album.setTitle("teste");
                    albumManager.save(album);
                    break;
                case "albumR":
                    List<Album> listaAlbums = albumManager.getAll();
                    session.setAttribute("listaAlbums", listaAlbums);
                    break;
                case "albumU":
                    album = new Album();
                    album.setId(16);
                    album.setLastFmId("teste2");
                    album.setTitle("teste2");
                    albumManager.update(album);
                    break;
                case "albumD":
                    album = new Album();
                    album.setId(16);
                    album.setLastFmId("teste2");
                    album.setTitle("teste2");
                    albumManager.delete(album);
                    break;
                case "artistaC":
                    artist = new Artist();
                    artist.setId(100);
                    artist.setLastFmId("teste");
                    artist.setName("teste");
                    artistaManager.save(artist);
                    break;
                case "artistaR":
                    List<Artist> listaArtists = artistaManager.getAll();
                    session.setAttribute("listaArtistas", listaArtists);
                    break;
                case "artistaU":
                    artist = new Artist();
                    artist.setId(36);
                    artist.setLastFmId("teste2");
                    artist.setName("teste2");
                    artistaManager.update(artist);
                    break;
                case "artistaD":
                    artist = new Artist();
                    artist.setId(36);
                    artist.setLastFmId("teste2");
                    artist.setName("teste2");
                    artistaManager.delete(artist);
                    break;
                case "generoJogoC":
                    gameGenre = new GameGenre();
                    gameGenre.setId(100);
                    gameGenre.setName("teste");
                    generoJogoManager.save(gameGenre);
                    break;
                case "generoJogoR":
                    List<GameGenre> listaGenerosJogos = generoJogoManager.getAll();
                    session.setAttribute("listaGenerosJogos", listaGenerosJogos);
                    break;
                case "generoJogoU":
                    gameGenre = new GameGenre();
                    gameGenre.setId(85);
                    gameGenre.setName("teste2");
                    generoJogoManager.update(gameGenre);
                    break;
                case "generoJogoD":
                    gameGenre = new GameGenre();
                    gameGenre.setId(85);
                    gameGenre.setName("teste2");
                    generoJogoManager.delete(gameGenre);
                    break;
                case "musicaC":
                    music = new Music();
                    music.setId(100);
                    music.setLastFmId("teste");
                    music.setTitle("teste");
                    musicaManager.save(music);
                    break;
                case "musicaR":
                    List<Music> listaMusics = musicaManager.getAll();
                    session.setAttribute("listaMusicas", listaMusics);
                    break;
                case "musicaU":
                    music = new Music();
                    music.setId(40);
                    music.setLastFmId("teste2");
                    music.setTitle("teste2");
                    musicaManager.update(music);
                    break;
                case "musicaD":
                    music = new Music();
                    music.setId(40);
                    music.setLastFmId("teste2");
                    music.setTitle("teste2");
                    musicaManager.delete(music);
                    break;
                case "relacaoC":
                    musicReleaseAndGameMap = new MusicReleaseAndGameMap();
                    musicReleaseAndGameMap.setId(100);
                    musicReleaseAndGameMap.setAlbumId(null);
                    musicReleaseAndGameMap.setArtistId(null);
                    musicReleaseAndGameMap.setGameGenreId(null);
                    musicReleaseAndGameMap.setMusicId(null);
                    relacaoManager.save(musicReleaseAndGameMap);
                    break;
                case "relacaoR":
                    List<MusicReleaseAndGameMap> listaRelacoes = relacaoManager.getAll();
                    session.setAttribute("listaRelacoes", listaRelacoes);
                    break;
                case "relacaoU":
                    musicReleaseAndGameMap = new MusicReleaseAndGameMap();
                    musicReleaseAndGameMap.setId(86);
                    musicReleaseAndGameMap.setAlbumId(null);
                    musicReleaseAndGameMap.setArtistId(null);
                    GameGenre gameGenre2 = new GameGenre();
                    gameGenre2.setId(4);
                    musicReleaseAndGameMap.setGameGenreId(gameGenre);
                    musicReleaseAndGameMap.setMusicId(null);
                    relacaoManager.update(musicReleaseAndGameMap);
                    break;
                case "relacaoD":
                    musicReleaseAndGameMap = new MusicReleaseAndGameMap();
                    musicReleaseAndGameMap.setId(86);
                    musicReleaseAndGameMap.setAlbumId(null);
                    musicReleaseAndGameMap.setArtistId(null);
                    GameGenre gameGenre3 = new GameGenre();
                    gameGenre3.setId(4);
                    musicReleaseAndGameMap.setGameGenreId(gameGenre);
                    musicReleaseAndGameMap.setMusicId(null);
                    relacaoManager.delete(musicReleaseAndGameMap);
                    break;
                case "usuarioC":
                    user = new User();
                    user.setLogin("teste");
                    user.setPassword("teste");
                    user.setSteanUser("teste");
                    usuarioManager.save(user);
                    break;
                case "usuarioR":
                    List<User> listaUsers = usuarioManager.getAll();
                    session.setAttribute("listaUsuarios", listaUsers);
                    break;
                case "usuarioU":
                    user = new User();
                    user.setId(27);
                    user.setLogin("teste2");
                    user.setPassword("teste2");
                    user.setSteanUser("teste2");
                    usuarioManager.update(user);
                    break;
                case "usuarioD":
                    user = new User();
                    user.setId(27);
                    user.setLogin("teste2");
                    user.setPassword("teste2");
                    user.setSteanUser("teste2");
                    usuarioManager.delete(user);
                    break;
            }
            response.sendRedirect("indexTeste.jsp");
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
