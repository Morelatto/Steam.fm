package br.com.lp3.utilities;

import br.com.lp3.rmi.manager.AlbumManager;
import br.com.lp3.rmi.manager.ArtistManager;
import br.com.lp3.rmi.manager.GameGenreManager;
import br.com.lp3.rmi.manager.GameManager;
import br.com.lp3.rmi.manager.LoginManager;
import br.com.lp3.rmi.manager.MusicManager;
import br.com.lp3.rmi.manager.RecommendationManager;
import br.com.lp3.rmi.manager.MusicReleaseAndGameMapManager;
import br.com.lp3.rmi.manager.UserManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

    private static ServiceLocator instance;
    private final String PREFIX = "ejblocal:";

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public AlbumManager getAlbumLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (AlbumManager) context.lookup(PREFIX + AlbumManager.class.getName());
    }

    public MusicManager getMusicaLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (MusicManager) context.lookup(PREFIX + MusicManager.class.getName());
    }

    public UserManager getUsuarioLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (UserManager) context.lookup(PREFIX + UserManager.class.getName());
    }

    public RecommendationManager getRecomendacaoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (RecommendationManager) context.lookup(PREFIX + RecommendationManager.class.getName());
    }

    public MusicReleaseAndGameMapManager getRelacaoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (MusicReleaseAndGameMapManager) context.lookup(PREFIX + MusicReleaseAndGameMapManager.class.getName());
    }

    public LoginManager getLoginLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (LoginManager) context.lookup(PREFIX + LoginManager.class.getName());
    }

    public GameManager getJogoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (GameManager) context.lookup(PREFIX + GameManager.class.getName());
    }

    public GameGenreManager getGeneroJogoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (GameGenreManager) context.lookup(PREFIX + GameGenreManager.class.getName());
    }

    public ArtistManager getArtistaLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (ArtistManager) context.lookup(PREFIX + ArtistManager.class.getName());
    }
}
