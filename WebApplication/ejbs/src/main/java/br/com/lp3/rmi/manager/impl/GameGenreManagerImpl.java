package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.GameGenreJSONParser;
import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.dto.Game;
import br.com.lp3.rmi.dao.RemoteDAO;
import br.com.lp3.rmi.dao.RemoteDAOOperations;
import br.com.lp3.rmi.manager.GameGenreManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class GameGenreManagerImpl extends UnicastRemoteObject implements GameGenreManager {

    private RemoteDAOOperations<GameGenre> operations;

    public GameGenreManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("GameGenreDAO");
        operations = new RemoteDAOOperations<GameGenre>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<GameGenre> getOperations() {
        return operations;
    }

    @Override
    public List<GameGenre> getGameGenres(List<Game> gameList) {
        return getGameGenreListByGameGenreNames(GameGenreJSONParser.getListaGenerosBySteamId(gameList));
    }

    @Override
    public List<GameGenre> getGameGenreListByGameGenreNames(List<String> gameGenreNames) {
        List<GameGenre> listaGenerosObjeto = new ArrayList<>();
        for (GameGenre gameGenre : operations.getAll()) {
            if (gameGenreNames.indexOf(gameGenre.getName()) != -1) {
                listaGenerosObjeto.add(gameGenre);
            }
        }
        return listaGenerosObjeto;
    }

}
