package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.GameGenreToMusicRelease;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteGameGenreToMusicReleaseDAO extends UnicastRemoteObject implements RemoteDAO<GameGenreToMusicRelease> {

    private final GameGenreToMusicReleaseDAO gameGenreToMusicReleaseDAO;

    public RemoteGameGenreToMusicReleaseDAO() throws RemoteException {
        gameGenreToMusicReleaseDAO = new GameGenreToMusicReleaseDAO();
    }

    @Override
    public Optional<GameGenreToMusicRelease> get(Long id) {
        return gameGenreToMusicReleaseDAO.get(id);
    }

    @Override
    public List<GameGenreToMusicRelease> getAll() {
        return gameGenreToMusicReleaseDAO.getAll();
    }

    @Override
    public GameGenreToMusicRelease save(GameGenreToMusicRelease gameGenreToMusicRelease) {
        return gameGenreToMusicReleaseDAO.save(gameGenreToMusicRelease);
    }

    @Override
    public void update(GameGenreToMusicRelease gameGenreToMusicRelease) {
        gameGenreToMusicReleaseDAO.update(gameGenreToMusicRelease);
    }

    @Override
    public void delete(GameGenreToMusicRelease gameGenreToMusicRelease) {
        gameGenreToMusicReleaseDAO.delete(gameGenreToMusicRelease);
    }

}
