package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Music;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteMusicDAO extends UnicastRemoteObject implements RemoteDAO<Music> {

    private final MusicDAO musicDAO;

    public RemoteMusicDAO() throws RemoteException {
        musicDAO = new MusicDAO();
    }

    @Override
    public Optional<Music> get(long id) {
        return musicDAO.get(id);
    }

    @Override
    public List<Music> getAll() {
        return musicDAO.getAll();
    }

    @Override
    public void save(Music music) {
        musicDAO.save(music);
    }

    @Override
    public void update(Music music) {
        musicDAO.update(music);
    }

    @Override
    public void delete(Music music) {
        musicDAO.delete(music);
    }

}
