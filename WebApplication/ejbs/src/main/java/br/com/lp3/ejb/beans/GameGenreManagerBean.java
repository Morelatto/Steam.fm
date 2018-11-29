package br.com.lp3.ejb.beans;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.ejb.GameGenreManager;
import br.com.lp3.entities.GameGenre;
import br.com.lp3.utilities.RemoteDAOOperations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_PORT;

@Stateless
public class GameGenreManagerBean implements GameGenreManager {

    private RemoteDAOOperations<GameGenre> operations;

    public GameGenreManagerBean() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("GameGenreDAO");
        operations = new RemoteDAOOperations<GameGenre>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<GameGenre> getOperations() {
        return operations;
    }

    @Override
    public GameGenre getByName(String name) {
        return operations
                .getAll()
                .stream()
                .filter(gameGenre -> name.equalsIgnoreCase(gameGenre.getName()))
                .findFirst()
                .orElse(null);
    }

}
