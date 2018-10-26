package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Artista;
import br.com.lp3.dao.impl.ArtistaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteArtistaDAO extends UnicastRemoteObject implements RemoteDAO<Artista> {

    private final ArtistaDAO ArtistaDAO;

    public RemoteArtistaDAO() throws RemoteException {
        ArtistaDAO = new ArtistaDAO();
    }

    @Override
    public Optional<Artista> get(long id) {
        return ArtistaDAO.get(id);
    }

    @Override
    public List<Artista> getAll() {
        return ArtistaDAO.getAll();
    }

    @Override
    public void save(Artista artista) {
        ArtistaDAO.save(artista);
    }

    @Override
    public void update(Artista artista) {
        ArtistaDAO.update(artista);
    }

    @Override
    public void delete(Artista artista) {
        ArtistaDAO.delete(artista);
    }

}
