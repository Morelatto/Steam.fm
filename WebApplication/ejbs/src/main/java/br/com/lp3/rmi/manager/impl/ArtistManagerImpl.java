package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.Artist;
import br.com.lp3.rmi.RemoteDAO;
import br.com.lp3.rmi.manager.ArtistManager;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class ArtistManagerImpl extends UnicastRemoteObject implements ArtistManager {

    private RemoteDAO remoteDAO;

    public ArtistManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        remoteDAO = (RemoteDAO) registry.lookup("ArtistDAO");
    }

    @Override
    public Optional<Artist> get(long id) {
        return remoteDAO.get(id);
    }

    @Override
    public List<Artist> getAll() {
        return remoteDAO.getAll();
    }

    @Override
    public void save(Artist artist) {
        remoteDAO.save(artist);
    }

    @Override
    public void update(Artist artist) {
        remoteDAO.update(artist);
    }

    @Override
    public void delete(Artist artist) {
        remoteDAO.delete(artist);
    }

}
