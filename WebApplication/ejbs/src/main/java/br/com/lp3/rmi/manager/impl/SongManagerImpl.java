package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.Song;
import br.com.lp3.rmi.dao.RemoteDAO;
import br.com.lp3.rmi.dao.RemoteDAOOperations;
import br.com.lp3.rmi.manager.SongManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class SongManagerImpl extends UnicastRemoteObject implements SongManager {

    private RemoteDAOOperations<Song> operations;

    public SongManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("SongDAO");
        operations = new RemoteDAOOperations<Song>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<Song> getOperations() {
        return operations;
    }

}
