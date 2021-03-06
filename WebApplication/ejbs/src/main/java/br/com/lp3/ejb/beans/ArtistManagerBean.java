package br.com.lp3.ejb.beans;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.ejb.ArtistManager;
import br.com.lp3.entities.Artist;
import br.com.lp3.utilities.RemoteDAOOperations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_PORT;

@Stateless
public class ArtistManagerBean implements ArtistManager {

    private RemoteDAOOperations<Artist> operations;

    public ArtistManagerBean() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("ArtistDAO");
        operations = new RemoteDAOOperations<Artist>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<Artist> getOperations() {
        return operations;
    }

}
