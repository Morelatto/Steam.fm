package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Song;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteSongDAO extends UnicastRemoteObject implements RemoteDAO<Song> {

    private final SongDAO songDAO;

    public RemoteSongDAO() throws RemoteException {
        songDAO = new SongDAO();
    }

    @Override
    public Optional<Song> get(Long id) {
        return songDAO.get(id);
    }

    @Override
    public List<Song> getAll() {
        return songDAO.getAll();
    }

    @Override
    public void save(Song song) {
        songDAO.save(song);
    }

    @Override
    public void update(Song song) {
        songDAO.update(song);
    }

    @Override
    public void delete(Song song) {
        songDAO.delete(song);
    }

}
