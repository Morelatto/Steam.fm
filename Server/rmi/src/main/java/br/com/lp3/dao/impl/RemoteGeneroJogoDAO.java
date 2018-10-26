package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.GeneroJogo;
import br.com.lp3.dao.impl.GeneroJogoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteGeneroJogoDAO extends UnicastRemoteObject implements RemoteDAO<GeneroJogo> {

    private final GeneroJogoDAO GeneroJogoDAO;

    public RemoteGeneroJogoDAO() throws RemoteException {
        GeneroJogoDAO = new GeneroJogoDAO();
    }

    @Override
    public Optional<GeneroJogo> get(long id) {
        return GeneroJogoDAO.get(id);
    }

    @Override
    public List<GeneroJogo> getAll() {
        return GeneroJogoDAO.getAll();
    }

    @Override
    public void save(GeneroJogo generoJogo) {
        GeneroJogoDAO.save(generoJogo);
    }

    @Override
    public void update(GeneroJogo generoJogo) {
        GeneroJogoDAO.update(generoJogo);
    }

    @Override
    public void delete(GeneroJogo generoJogo) {
        GeneroJogoDAO.delete(generoJogo);
    }

}
