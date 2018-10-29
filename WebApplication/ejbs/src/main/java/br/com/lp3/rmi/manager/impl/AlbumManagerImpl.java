package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.Album;
import br.com.lp3.rmi.RemoteDAO;
import br.com.lp3.rmi.manager.AlbumManager;

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
public class AlbumManagerImpl extends UnicastRemoteObject implements AlbumManager {

    private RemoteDAO remoteDAO;

    public AlbumManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        remoteDAO = (RemoteDAO) registry.lookup("AlbumDAO");
    }

    @Override
    public Optional<Album> get(long id) {
        return remoteDAO.get(id);
    }

    @Override
    public List<Album> getAll() {
        return remoteDAO.getAll();
    }

    @Override
    public void save(Album album) {
        remoteDAO.save(album);
    }

    @Override
    public void update(Album album) {
        remoteDAO.update(album);
    }

    @Override
    public void delete(Album album) {
        remoteDAO.delete(album);
    }

}
