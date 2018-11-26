package br.com.lp3.controller;

import br.com.lp3.rmi.manager.AlbumManager;
import br.com.lp3.rmi.manager.ArtistManager;
import br.com.lp3.rmi.manager.GameGenreManager;
import br.com.lp3.rmi.manager.SongManager;
import br.com.lp3.rmi.manager.GameGenreToMusicReleaseManager;
import br.com.lp3.rmi.manager.SteamFmUserManager;
import br.com.lp3.utilities.ServiceLocator;
import br.com.lp3.entities.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TestController extends HttpServlet {

    private AlbumManager albumManager;
    private ArtistManager artistManager;
    private GameGenreManager gameGenreManager;
    private SongManager songManager;
    private GameGenreToMusicReleaseManager gameGenreToMusicReleaseManager;
    private SteamFmUserManager steamFmUserManager;

    public TestController() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        gameGenreManager = serviceLocator.getGameGenreManager();
        gameGenreToMusicReleaseManager = serviceLocator.getMusicReleaseAndGameMapManager();
        albumManager = serviceLocator.getAlbumManager();
        artistManager = serviceLocator.getArtistManagerLocal();
        songManager = serviceLocator.getSongManager();
        steamFmUserManager = serviceLocator.getSteamFmUser();
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

        if (command != null) {
            // char operation = command.charAt(command.length() - 1);
            // if (command.startsWith("album")) {
            // } else if (command.startsWith("artist")) {
            // } else if (command.startsWith("song")) {
            // } else if (command.startsWith("gameGenre")) {
            // } else if (command.startsWith("gameGenreToMusicRelease")) {
            // } else if (command.startsWith("steamFmUser")) {
            // }

            switch (command) {
            case "albumC":
                Album album = new Album();
                album.setLastFmId("teste");
                album.setName("teste");
                albumManager.getOperations().save(album);
                break;
            case "albumR":
                List<Album> listaAlbums = albumManager.getOperations().getAll();
                session.setAttribute("listaAlbums", listaAlbums);
                break;
            case "albumU":
                album = new Album();
                album.setLastFmId("teste2");
                album.setName("teste2");
                albumManager.getOperations().update(album);
                break;
            case "albumD":
                album = new Album();
                album.setLastFmId("teste2");
                album.setName("teste2");
                albumManager.getOperations().delete(album);
                break;
            case "artistaC":
                Artist artist = new Artist();
                artist.setLastFmId("teste");
                artist.setName("teste");
                artistManager.getOperations().save(artist);
                break;
            case "artistaR":
                List<Artist> listaArtists = artistManager.getOperations().getAll();
                session.setAttribute("listaArtistas", listaArtists);
                break;
            case "artistaU":
                artist = new Artist();
                artist.setLastFmId("teste2");
                artist.setName("teste2");
                artistManager.getOperations().update(artist);
                break;
            case "artistaD":
                artist = new Artist();
                artist.setLastFmId("teste2");
                artist.setName("teste2");
                artistManager.getOperations().delete(artist);
                break;
            case "generoJogoC":
                GameGenre gameGenre = new GameGenre();
                gameGenre.setId(100L);
                gameGenre.setName("teste");
                gameGenreManager.getOperations().save(gameGenre);
                break;
            case "generoJogoR":
                List<GameGenre> listaGenerosJogos = gameGenreManager.getOperations().getAll();
                session.setAttribute("listaGenerosJogos", listaGenerosJogos);
                break;
            case "generoJogoU":
                gameGenre = new GameGenre();
                gameGenre.setId(85L);
                gameGenre.setName("teste2");
                gameGenreManager.getOperations().update(gameGenre);
                break;
            case "generoJogoD":
                gameGenre = new GameGenre();
                gameGenre.setId(85L);
                gameGenre.setName("teste2");
                gameGenreManager.getOperations().delete(gameGenre);
                break;
            case "musicaC":
                Song song = new Song();
                song.setLastFmId("teste");
                song.setName("teste");
                songManager.getOperations().save(song);
                break;
            case "musicaR":
                List<Song> listaSongs = songManager.getOperations().getAll();
                session.setAttribute("listaMusicas", listaSongs);
                break;
            case "musicaU":
                song = new Song();
                song.setLastFmId("teste2");
                song.setName("teste2");
                songManager.getOperations().update(song);
                break;
            case "musicaD":
                song = new Song();
                song.setLastFmId("teste2");
                song.setName("teste2");
                songManager.getOperations().delete(song);
                break;
            case "relacaoC":
                GameGenreToMusicRelease gameGenreToMusicRelease = new GameGenreToMusicRelease();
                gameGenreToMusicRelease.setId(100L);
                gameGenreToMusicRelease.setAlbum(null);
                gameGenreToMusicRelease.setArtist(null);
                gameGenreToMusicRelease.setGameGenre(null);
                gameGenreToMusicRelease.setSong(null);
                gameGenreToMusicReleaseManager.getOperations().save(gameGenreToMusicRelease);
                break;
            case "relacaoR":
                List<GameGenreToMusicRelease> listaRelacoes = gameGenreToMusicReleaseManager.getOperations().getAll();
                session.setAttribute("listaRelacoes", listaRelacoes);
                break;
            case "relacaoU":
                gameGenreToMusicRelease = new GameGenreToMusicRelease();
                gameGenreToMusicRelease.setId(86L);
                gameGenreToMusicRelease.setAlbum(null);
                gameGenreToMusicRelease.setArtist(null);
                GameGenre gameGenre2 = new GameGenre();
                gameGenre2.setId(4L);
                gameGenreToMusicRelease.setGameGenre(gameGenre2);
                gameGenreToMusicRelease.setSong(null);
                gameGenreToMusicReleaseManager.getOperations().update(gameGenreToMusicRelease);
                break;
            case "relacaoD":
                gameGenreToMusicRelease = new GameGenreToMusicRelease();
                gameGenreToMusicRelease.setId(86L);
                gameGenreToMusicRelease.setAlbum(null);
                gameGenreToMusicRelease.setArtist(null);
                GameGenre gameGenre3 = new GameGenre();
                gameGenre3.setId(4L);
                gameGenreToMusicRelease.setGameGenre(gameGenre3);
                gameGenreToMusicRelease.setSong(null);
                gameGenreToMusicReleaseManager.getOperations().delete(gameGenreToMusicRelease);
                break;
            case "usuarioC":
                SteamFmUser steamFmUser = new SteamFmUser();
                steamFmUser.setLogin("teste");
                steamFmUser.setPassword("teste");
                steamFmUser.setSteamUser("teste");
                steamFmUserManager.getOperations().save(steamFmUser);
                break;
            case "usuarioR":
                List<SteamFmUser> listaSteamFmUsers = steamFmUserManager.getOperations().getAll();
                session.setAttribute("listaUsuarios", listaSteamFmUsers);
                break;
            case "usuarioU":
                steamFmUser = new SteamFmUser();
                steamFmUser.setId(27L);
                steamFmUser.setLogin("teste2");
                steamFmUser.setPassword("teste2");
                steamFmUser.setSteamUser("teste2");
                steamFmUserManager.getOperations().update(steamFmUser);
                break;
            case "usuarioD":
                steamFmUser = new SteamFmUser();
                steamFmUser.setId(27L);
                steamFmUser.setLogin("teste2");
                steamFmUser.setPassword("teste2");
                steamFmUser.setSteamUser("teste2");
                steamFmUserManager.getOperations().delete(steamFmUser);
                break;
            }
            response.sendRedirect("indexTeste.jsp");
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
        return "Test controller";
    }

}
