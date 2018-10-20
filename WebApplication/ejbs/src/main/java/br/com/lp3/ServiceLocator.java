package br.com.lp3;

import br.com.lp3.rmi.*;

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

    public AlbumManagerLocal getAlbumLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (AlbumManagerLocal) context.lookup(PREFIX + AlbumManagerLocal.class.getName());
    }

    public MusicaManagerLocal getMusicaLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (MusicaManagerLocal) context.lookup(PREFIX + MusicaManagerLocal.class.getName());
    }

    public UsuarioManagerLocal getUsuarioLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (UsuarioManagerLocal) context.lookup(PREFIX + UsuarioManagerLocal.class.getName());
    }

    public RecomendacaoManagerLocal getRecomendacaoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (RecomendacaoManagerLocal) context.lookup(PREFIX + RecomendacaoManagerLocal.class.getName());
    }

    public RelacaoManagerLocal getRelacaoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (RelacaoManagerLocal) context.lookup(PREFIX + RelacaoManagerLocal.class.getName());
    }

    public LoginManagerLocal getLoginLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (LoginManagerLocal) context.lookup(PREFIX + LoginManagerLocal.class.getName());
    }

    public JogoManagerLocal getJogoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (JogoManagerLocal) context.lookup(PREFIX + JogoManagerLocal.class.getName());
    }

    public GeneroJogoManagerLocal getGeneroJogoLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (GeneroJogoManagerLocal) context.lookup(PREFIX + GeneroJogoManagerLocal.class.getName());
    }

    public ArtistaManagerLocal getArtistaLocal() throws NamingException {
        InitialContext context = new InitialContext();
        return (ArtistaManagerLocal) context.lookup(PREFIX + ArtistaManagerLocal.class.getName());
    }
}
