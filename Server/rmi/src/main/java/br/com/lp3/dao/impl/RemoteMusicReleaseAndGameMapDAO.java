package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.MusicReleaseAndGameMap;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteMusicReleaseAndGameMapDAO extends UnicastRemoteObject implements RemoteDAO<MusicReleaseAndGameMap> {

    private final MusicReleaseAndGameMapDAO musicReleaseAndGameMapDAO;

    public RemoteMusicReleaseAndGameMapDAO() throws RemoteException {
        musicReleaseAndGameMapDAO = new MusicReleaseAndGameMapDAO();
    }

    @Override
    public Optional<MusicReleaseAndGameMap> get(long id) {
        return musicReleaseAndGameMapDAO.get(id);
    }

    @Override
    public List<MusicReleaseAndGameMap> getAll() {
        return musicReleaseAndGameMapDAO.getAll();
    }

    @Override
    public void save(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        musicReleaseAndGameMapDAO.save(musicReleaseAndGameMap);
    }

    @Override
    public void update(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        musicReleaseAndGameMapDAO.update(musicReleaseAndGameMap);
    }

    @Override
    public void delete(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        musicReleaseAndGameMapDAO.delete(musicReleaseAndGameMap);
    }

}
