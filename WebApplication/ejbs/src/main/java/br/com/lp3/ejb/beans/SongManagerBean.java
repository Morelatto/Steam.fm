package br.com.lp3.ejb.beans;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.ejb.SongManager;
import br.com.lp3.entities.Song;
import br.com.lp3.utilities.RemoteDAOOperations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_PORT;

@Stateless
public class SongManagerBean implements SongManager {

    private RemoteDAOOperations<Song> operations;

    public SongManagerBean() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("SongDAO");
        operations = new RemoteDAOOperations<Song>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<Song> getOperations() {
        return operations;
    }

}
