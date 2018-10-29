package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.GameGenreJSONParser;
import br.com.lp3.entities.GameGenre;
import br.com.lp3.rmi.RemoteDAO;
import br.com.lp3.rmi.manager.GameGenreManager;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class GameGenreManagerImpl extends UnicastRemoteObject implements GameGenreManager {

    private RemoteDAO remoteDAO;

    public GameGenreManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        remoteDAO = (RemoteDAO) registry.lookup("GameGenreDAO");
    }

    @Override
    public Optional<GameGenre> get(long id) {
        return remoteDAO.get(id);
    }

    @Override
    public List<GameGenre> getAll() {
        return remoteDAO.getAll();
    }

    @Override
    public void save(GameGenre gameGenre) {
        remoteDAO.save(gameGenre);
    }

    @Override
    public void update(GameGenre gameGenre) {
        remoteDAO.update(gameGenre);
    }

    @Override
    public void delete(GameGenre gameGenre) {
        remoteDAO.delete(gameGenre);
    }
    
    @Override
    public List<GameGenre> getGameGenresByUser(String username) {
        return getGameGenreListByGameGenreNames(GameGenreJSONParser.getListaGenerosByUser(username));
    }

    @Override
    public List<GameGenre> getGameGenreListByGameGenreNames(List<String> gameGenreNames) {
        List<GameGenre> listaGenerosObjeto = new ArrayList<>();
        for (GameGenre gameGenre : getAll()) {
            if (gameGenreNames.indexOf(gameGenre.getName()) != -1) {
                listaGenerosObjeto.add(gameGenre);
            }
        }
        return listaGenerosObjeto;
    }

}
