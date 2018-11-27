package br.com.lp3.ejb.beans;

import br.com.lp3.ejb.AlbumManager;
import br.com.lp3.entities.Album;
import br.com.lp3.rmi.RemoteDAO;
import br.com.lp3.rmi.RemoteDAOOperations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_PORT;

@Stateless
public class AlbumManagerBean implements AlbumManager {

    private RemoteDAOOperations<Album> operations;

    public AlbumManagerBean() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("AlbumDAO");
        operations = new RemoteDAOOperations<Album>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<Album> getOperations() {
        return operations;
    }

}
