package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Album;
import br.com.lp3.dao.impl.AlbumDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteAlbumDAO extends UnicastRemoteObject implements RemoteDAO<Album> {

    private final AlbumDAO albumDAO;

    public RemoteAlbumDAO() throws RemoteException {
        albumDAO = new AlbumDAO();
    }

    @Override
    public Optional<Album> get(long id) {
        return albumDAO.get(id);
    }

    @Override
    public List<Album> getAll() {
        return albumDAO.getAll();
    }

    @Override
    public void save(Album album) {
        albumDAO.save(album);
    }

    @Override
    public void update(Album album) {
        albumDAO.update(album);
    }

    @Override
    public void delete(Album album) {
        albumDAO.delete(album);
    }

}
