package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.MusicReleaseAndGameMap;
import br.com.lp3.rmi.RemoteDAO;
import br.com.lp3.rmi.manager.MusicReleaseAndGameMapManager;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class MusicReleaseAndGameMapManagerImpl extends UnicastRemoteObject implements MusicReleaseAndGameMapManager {

    private RemoteDAO remoteDAO;

    public MusicReleaseAndGameMapManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        remoteDAO = (RemoteDAO) registry.lookup("MusicReleaseAndGameMapDAO");
    }

    @Override
    public Optional<MusicReleaseAndGameMap> get(long id) {
        return remoteDAO.get(id);
    }

    @Override
    public List<MusicReleaseAndGameMap> getAll() {
        return remoteDAO.getAll();
    }

    @Override
    public void save(MusicReleaseAndGameMap MusicReleaseAndGameMap) {
        remoteDAO.save(MusicReleaseAndGameMap);
    }

    @Override
    public void update(MusicReleaseAndGameMap MusicReleaseAndGameMap) {
        remoteDAO.update(MusicReleaseAndGameMap);
    }

    @Override
    public void delete(MusicReleaseAndGameMap MusicReleaseAndGameMap) {
        remoteDAO.delete(MusicReleaseAndGameMap);
    }

    @Override
    public List<MusicReleaseAndGameMap> getListaRelacao(List<GameGenre> listaGameGenres) {
        List<MusicReleaseAndGameMap> listaMusicReleaseAndGameMap = new ArrayList<>();
        for (MusicReleaseAndGameMap musicReleaseAndGameMap : getAll()) {
            for (GameGenre gameGenre : listaGameGenres) {
                if (Objects.equals(gameGenre.getId(), musicReleaseAndGameMap.getGameGenreId().getId())) {
                    listaMusicReleaseAndGameMap.add(musicReleaseAndGameMap);
                    break;
                }
            }
        }
        return listaMusicReleaseAndGameMap;
    }

}
