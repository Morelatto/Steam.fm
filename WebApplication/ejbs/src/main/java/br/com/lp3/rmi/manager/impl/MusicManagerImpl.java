package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.Music;
import br.com.lp3.rmi.RemoteDAO;
import br.com.lp3.rmi.manager.MusicManager;

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
public class MusicManagerImpl extends UnicastRemoteObject implements MusicManager {

    private RemoteDAO remoteDAO;

    public MusicManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        remoteDAO = (RemoteDAO) registry.lookup("MusicDAO");
    }

    @Override
    public Optional<Music> get(long id) {
        return remoteDAO.get(id);
    }

    @Override
    public List<Music> getAll() {
        return remoteDAO.getAll();
    }

    @Override
    public void save(Music music) {
        remoteDAO.save(music);
    }

    @Override
    public void update(Music music) {
        remoteDAO.update(music);
    }

    @Override
    public void delete(Music music) {
        remoteDAO.delete(music);
    }

}
