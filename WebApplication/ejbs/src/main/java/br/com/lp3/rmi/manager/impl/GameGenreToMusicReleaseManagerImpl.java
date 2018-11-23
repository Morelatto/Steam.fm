package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.GameGenreToMusicRelease;
import br.com.lp3.rmi.dao.RemoteDAO;
import br.com.lp3.rmi.dao.RemoteDAOOperations;
import br.com.lp3.rmi.manager.GameGenreToMusicReleaseManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class GameGenreToMusicReleaseManagerImpl extends UnicastRemoteObject implements GameGenreToMusicReleaseManager {

    private RemoteDAOOperations<GameGenreToMusicRelease> operations;

    public GameGenreToMusicReleaseManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("GameGenreToMusicReleaseDAO");
        operations = new RemoteDAOOperations<GameGenreToMusicRelease>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<GameGenreToMusicRelease> getOperations() {
        return operations;
    }

    @Override
    public List<GameGenreToMusicRelease> getListaRelacao(List<GameGenre> listaGameGenres) {
        List<GameGenreToMusicRelease> listaGameGenreToMusicRelease = new ArrayList<>();
        for (GameGenreToMusicRelease gameGenreToMusicRelease : operations.getAll()) {
            for (GameGenre gameGenre : listaGameGenres) {
                if (Objects.equals(gameGenre.getId(), gameGenreToMusicRelease.getGameGenreId().getId())) {
                    listaGameGenreToMusicRelease.add(gameGenreToMusicRelease);
                    break;
                }
            }
        }
        return listaGameGenreToMusicRelease;
    }

}
