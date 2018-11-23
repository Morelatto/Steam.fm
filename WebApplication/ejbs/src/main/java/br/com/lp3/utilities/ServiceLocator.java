package br.com.lp3.utilities;

import br.com.lp3.rmi.manager.AlbumManager;
import br.com.lp3.rmi.manager.ArtistManager;
import br.com.lp3.rmi.manager.GameGenreManager;
import br.com.lp3.rmi.manager.GameManager;
import br.com.lp3.rmi.manager.LoginManager;
import br.com.lp3.rmi.manager.SongManager;
import br.com.lp3.rmi.manager.RecommendationManager;
import br.com.lp3.rmi.manager.GameGenreToMusicReleaseManager;
import br.com.lp3.rmi.manager.SteamFmUserManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

    private static ServiceLocator instance;
    private static final String PREFIX = "java:global/myapp/";

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public AlbumManager getAlbumManager() {
        return jndiLookup(AlbumManager.class);
    }

    public SongManager getSongManager() {
        return jndiLookup(SongManager.class);
    }

    public SteamFmUserManager getSteamFmUser() {
        return jndiLookup(SteamFmUserManager.class);
    }

    public RecommendationManager getRecommendationManager() {
        return jndiLookup(RecommendationManager.class);
    }

    public GameGenreToMusicReleaseManager getMusicReleaseAndGameMapManager() {
        return jndiLookup(GameGenreToMusicReleaseManager.class);
    }

    public LoginManager getLoginManager() {
        return jndiLookup(LoginManager.class);
    }

    public GameManager getGameManager() {
        return jndiLookup(GameManager.class);
    }

    public GameGenreManager getGameGenreManager() {
        return jndiLookup(GameGenreManager.class);
    }

    public ArtistManager getArtistManagerLocal() {
        return jndiLookup(ArtistManager.class);
    }

    private <T> T jndiLookup(Class<T> clazz) {
        try {
            InitialContext context = new InitialContext();
            return clazz.cast(context.lookup(PREFIX + clazz.getSimpleName() + "Impl"));
        } catch (NamingException e) {
            // TODO log
            e.printStackTrace();
        }
        return null;
    }

}
