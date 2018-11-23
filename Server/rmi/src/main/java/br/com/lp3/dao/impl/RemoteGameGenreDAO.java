package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.GameGenre;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteGameGenreDAO extends UnicastRemoteObject implements RemoteDAO<GameGenre> {

    private final GameGenreDAO gameGenreDAO;

    public RemoteGameGenreDAO() throws RemoteException {
        gameGenreDAO = new GameGenreDAO();
    }

    @Override
    public Optional<GameGenre> get(Long id) {
        return gameGenreDAO.get(id);
    }

    @Override
    public List<GameGenre> getAll() {
        return gameGenreDAO.getAll();
    }

    @Override
    public void save(GameGenre gameGenre) {
        gameGenreDAO.save(gameGenre);
    }

    @Override
    public void update(GameGenre gameGenre) {
        gameGenreDAO.update(gameGenre);
    }

    @Override
    public void delete(GameGenre gameGenre) {
        gameGenreDAO.delete(gameGenre);
    }

}
