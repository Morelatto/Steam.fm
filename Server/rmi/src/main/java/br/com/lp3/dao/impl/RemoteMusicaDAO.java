package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Musica;
import br.com.lp3.dao.impl.MusicaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteMusicaDAO extends UnicastRemoteObject implements RemoteDAO<Musica> {

    private final MusicaDAO MusicaDAO;

    public RemoteMusicaDAO() throws RemoteException {
        MusicaDAO = new MusicaDAO();
    }

    @Override
    public Optional<Musica> get(long id) {
        return MusicaDAO.get(id);
    }

    @Override
    public List<Musica> getAll() {
        return MusicaDAO.getAll();
    }

    @Override
    public void save(Musica musica) {
        MusicaDAO.save(musica);
    }

    @Override
    public void update(Musica musica) {
        MusicaDAO.update(musica);
    }

    @Override
    public void delete(Musica musica) {
        MusicaDAO.delete(musica);
    }

}
