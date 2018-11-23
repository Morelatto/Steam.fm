package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.Artist;
import br.com.lp3.rmi.dao.RemoteDAO;
import br.com.lp3.rmi.dao.RemoteDAOOperations;
import br.com.lp3.rmi.manager.ArtistManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class ArtistManagerImpl extends UnicastRemoteObject implements ArtistManager {

    private RemoteDAOOperations<Artist> operations;

    public ArtistManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("ArtistDAO");
        operations = new RemoteDAOOperations<Artist>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<Artist> getOperations() {
        return operations;
    }

}
