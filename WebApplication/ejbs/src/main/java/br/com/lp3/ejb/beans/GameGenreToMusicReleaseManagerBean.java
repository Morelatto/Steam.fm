package br.com.lp3.ejb.beans;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.ejb.GameGenreToMusicReleaseManager;
import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.GameGenreToMusicRelease;
import br.com.lp3.utilities.RemoteDAOOperations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_PORT;

@Stateless
public class GameGenreToMusicReleaseManagerBean implements GameGenreToMusicReleaseManager {

    private RemoteDAOOperations<GameGenreToMusicRelease> operations;

    public GameGenreToMusicReleaseManagerBean() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("GameGenreToMusicReleaseDAO");
        operations = new RemoteDAOOperations<GameGenreToMusicRelease>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<GameGenreToMusicRelease> getOperations() {
        return operations;
    }

    @Override
    public GameGenreToMusicRelease getByGameGenre(GameGenre gameGenre) {
        return operations
                .getAll()
                .stream()
                .filter(gameGenreToMusicRelease -> gameGenreToMusicRelease.getGameGenre().equals(gameGenre))
                .findFirst()
                .orElse(null);
    }

}
