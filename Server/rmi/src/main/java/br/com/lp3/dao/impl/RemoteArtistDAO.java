package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Artist;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteArtistDAO extends UnicastRemoteObject implements RemoteDAO<Artist> {

    private final ArtistDAO artistDAO;

    public RemoteArtistDAO() throws RemoteException {
        artistDAO = new ArtistDAO();
    }

    @Override
    public Optional<Artist> get(Long id) {
        return artistDAO.get(id);
    }

    @Override
    public List<Artist> getAll() {
        return artistDAO.getAll();
    }

    @Override
    public void save(Artist artist) {
        artistDAO.save(artist);
    }

    @Override
    public void update(Artist artist) {
        artistDAO.update(artist);
    }

    @Override
    public void delete(Artist artist) {
        artistDAO.delete(artist);
    }

}
