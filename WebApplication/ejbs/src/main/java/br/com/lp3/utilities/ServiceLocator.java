package br.com.lp3.utilities;

import br.com.lp3.ejb.AlbumManager;
import br.com.lp3.ejb.ArtistManager;
import br.com.lp3.ejb.GameGenreManager;
import br.com.lp3.ejb.GameManager;
import br.com.lp3.ejb.LoginManager;
import br.com.lp3.ejb.SongManager;
import br.com.lp3.ejb.RecommendationManager;
import br.com.lp3.ejb.GameGenreToMusicReleaseManager;
import br.com.lp3.ejb.SteamFmUserManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * For this project one of the requirements were the manual EJB lookup using JNDI instead of using @EJB or @Inject
 * annotations.
 */
public class ServiceLocator {

    private static final String PREFIX = "java:module/";
    private static ServiceLocator instance;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public ArtistManager getArtistManager() {
        return jndiLookup(ArtistManager.class);
    }

    public AlbumManager getAlbumManager() {
        return jndiLookup(AlbumManager.class);
    }

    public SongManager getSongManager() {
        return jndiLookup(SongManager.class);
    }

    public GameManager getGameManager() {
        return jndiLookup(GameManager.class);
    }

    public GameGenreManager getGameGenreManager() {
        return jndiLookup(GameGenreManager.class);
    }

    public RecommendationManager getRecommendationManager() {
        return jndiLookup(RecommendationManager.class);
    }

    public GameGenreToMusicReleaseManager getGameGenreToMusicReleaseManager() {
        return jndiLookup(GameGenreToMusicReleaseManager.class);
    }

    public SteamFmUserManager getSteamFmUserManager() {
        return jndiLookup(SteamFmUserManager.class);
    }

    public LoginManager getLoginManager() {
        return jndiLookup(LoginManager.class);
    }

    private <T> T jndiLookup(Class<T> clazz) {
        try {
            InitialContext context = new InitialContext();
            return clazz.cast(context.lookup(PREFIX + clazz.getSimpleName() + "Bean"));
        } catch (NamingException e) {
            // TODO log
            e.printStackTrace();
        }
        return null;
    }

}
