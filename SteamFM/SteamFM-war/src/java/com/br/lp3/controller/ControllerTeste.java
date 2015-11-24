package com.br.lp3.controller;

import com.br.lp3.RMI.AlbumManagerLocal;
import com.br.lp3.RMI.ArtistaManagerLocal;
import com.br.lp3.RMI.GeneroJogoManagerLocal;
import com.br.lp3.RMI.MusicaManagerLocal;
import com.br.lp3.RMI.RelacaoManagerLocal;
import com.br.lp3.RMI.UsuarioManagerLocal;
import com.br.lp3.entities.Album;
import com.br.lp3.entities.Artista;
import com.br.lp3.entities.GeneroJogo;
import com.br.lp3.entities.Musica;
import com.br.lp3.entities.Relacao;
import com.br.lp3.entities.Usuario;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class ControllerTeste extends HttpServlet {
    
    @EJB
    private AlbumManagerLocal albumManager;
    @EJB
    private ArtistaManagerLocal artistaManager;
    @EJB
    private GeneroJogoManagerLocal generoJogoManager;
    @EJB
    private MusicaManagerLocal musicaManager;
    @EJB
    private RelacaoManagerLocal relacaoManager;
    @EJB
    private UsuarioManagerLocal usuarioManager;
    
    private Album album;
    private Artista artista;
    private GeneroJogo generoJogo;
    private Musica musica;
    private Relacao relacao;
    private Usuario usuario;
    
    private String command;
    private HttpSession session;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        command = request.getParameter("command");
        session = request.getSession();
        
        if (command != null) {
            switch (command) {
                case "albumC":
                    album = new Album();
                    album.setIdAlbum(100);
                    album.setIdAlbumLastfm("teste");
                    album.setTituloAlbum("teste");
                    albumManager.create(album);
                    break;
                case "albumR":
                    List<Album> listaAlbums = albumManager.read();
                    session.setAttribute("listaAlbums", listaAlbums);
                    break;
                case "albumU":
                    album = new Album();
                    album.setIdAlbum(16);
                    album.setIdAlbumLastfm("teste2");
                    album.setTituloAlbum("teste2");
                    albumManager.update(album);
                    break;
                case "albumD":
                    album = new Album();
                    album.setIdAlbum(16);
                    album.setIdAlbumLastfm("teste2");
                    album.setTituloAlbum("teste2");
                    albumManager.delete(album.getIdAlbum());
                    break;
                case "artistaC":
                    artista = new Artista();
                    artista.setIdArtista(100);
                    artista.setIdArtistaLastfm("teste");
                    artista.setNomeArtista("teste");
                    artistaManager.create(artista);
                    break;
                case "artistaR":
                    List<Artista> listaArtistas = artistaManager.read();
                    session.setAttribute("listaArtistas", listaArtistas);
                    break;
                case "artistaU":
                    artista = new Artista();
                    artista.setIdArtista(36);
                    artista.setIdArtistaLastfm("teste2");
                    artista.setNomeArtista("teste2");
                    artistaManager.update(artista);
                    break;
                case "artistaD":
                    artista = new Artista();
                    artista.setIdArtista(36);
                    artista.setIdArtistaLastfm("teste2");
                    artista.setNomeArtista("teste2");
                    artistaManager.delete(artista.getIdArtista());
                    break;
                case "generoJogoC":
                    generoJogo = new GeneroJogo();
                    generoJogo.setIdGeneroJogo(100);
                    generoJogo.setNomeGenero("teste");
                    generoJogoManager.create(generoJogo);
                    break;
                case "generoJogoR":
                    List<GeneroJogo> listaGenerosJogos = generoJogoManager.read();
                    session.setAttribute("listaGenerosJogos", listaGenerosJogos);
                    break;
                case "generoJogoU":
                    generoJogo = new GeneroJogo();
                    generoJogo.setIdGeneroJogo(85);
                    generoJogo.setNomeGenero("teste2");
                    generoJogoManager.update(generoJogo);
                    break;
                case "generoJogoD":
                    generoJogo = new GeneroJogo();
                    generoJogo.setIdGeneroJogo(85);
                    generoJogo.setNomeGenero("teste2");
                    generoJogoManager.delete(generoJogo.getIdGeneroJogo());
                    break;
                case "musicaC":
                    musica = new Musica();
                    musica.setIdMusica(100);
                    musica.setIdMusicaLastfm("teste");
                    musica.setTituloMusica("teste");
                    musicaManager.create(musica);
                    break;
                case "musicaR":
                    List<Musica> listaMusicas = musicaManager.read();
                    session.setAttribute("listaMusicas", listaMusicas);
                    break;
                case "musicaU":
                    musica = new Musica();
                    musica.setIdMusica(40);
                    musica.setIdMusicaLastfm("teste2");
                    musica.setTituloMusica("teste2");
                    musicaManager.update(musica);
                    break;
                case "musicaD":
                    musica = new Musica();
                    musica.setIdMusica(40);
                    musica.setIdMusicaLastfm("teste2");
                    musica.setTituloMusica("teste2");
                    musicaManager.delete(musica.getIdMusica());
                    break;
                case "relacaoC":
                    relacao = new Relacao();
                    relacao.setIdRelacao(100);
                    relacao.setIdAlbum(null);
                    relacao.setIdArtista(null);
                    relacao.setIdGeneroJogo(null);
                    relacao.setIdMusica(null);
                    relacaoManager.create(relacao);
                    break;
                case "relacaoR":
                    List<Relacao> listaRelacoes = relacaoManager.read();
                    session.setAttribute("listaRelacoes", listaRelacoes);
                    break;
                case "relacaoU":
                    relacao = new Relacao();
                    relacao.setIdRelacao(86);
                    relacao.setIdAlbum(null);
                    relacao.setIdArtista(null);
                    GeneroJogo generoJogo2 = new GeneroJogo();
                    generoJogo2.setIdGeneroJogo(4);
                    relacao.setIdGeneroJogo(generoJogo);
                    relacao.setIdMusica(null);
                    relacaoManager.update(relacao);
                    break;
                case "relacaoD":
                    relacao = new Relacao();
                    relacao.setIdRelacao(86);
                    relacao.setIdAlbum(null);
                    relacao.setIdArtista(null);
                    GeneroJogo generoJogo3 = new GeneroJogo();
                    generoJogo3.setIdGeneroJogo(4);
                    relacao.setIdGeneroJogo(generoJogo);
                    relacao.setIdMusica(null);
                    relacaoManager.delete(relacao.getIdRelacao());
                    break;
                case "usuarioC":
                    usuario = new Usuario();
                    usuario.setLogin("teste");
                    usuario.setSenha("teste");
                    usuario.setUsuarioSteam("teste");
                    usuarioManager.create(usuario);
                    break;
                case "usuarioR":
                    List<Usuario> listaUsuarios = usuarioManager.read();
                    session.setAttribute("listaUsuarios", listaUsuarios);
                    break;
                case "usuarioU":
                    usuario = new Usuario();
                    usuario.setIdUsuario(27);
                    usuario.setLogin("teste2");
                    usuario.setSenha("teste2");
                    usuario.setUsuarioSteam("teste2");
                    usuarioManager.update(usuario);
                    break;
                case "usuarioD":
                    usuario = new Usuario();
                    usuario.setIdUsuario(27);
                    usuario.setLogin("teste2");
                    usuario.setSenha("teste2");
                    usuario.setUsuarioSteam("teste2");
                    usuarioManager.delete(usuario.getIdUsuario());
                    break;
            }
            response.sendRedirect("indexTeste.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
