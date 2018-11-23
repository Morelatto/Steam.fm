package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.SteamFmUser;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteSteamFmUserDAO extends UnicastRemoteObject implements RemoteDAO<SteamFmUser> {

    private final SteamFmUserDAO steamFmUserDAO;

    public RemoteSteamFmUserDAO() throws RemoteException {
        steamFmUserDAO = new SteamFmUserDAO();
    }

    @Override
    public Optional<SteamFmUser> get(Long id) {
        return steamFmUserDAO.get(id);
    }

    @Override
    public List<SteamFmUser> getAll() {
        return steamFmUserDAO.getAll();
    }

    @Override
    public void save(SteamFmUser steamFmUser) {
        steamFmUserDAO.save(steamFmUser);
    }

    @Override
    public void update(SteamFmUser steamFmUser) {
        steamFmUserDAO.update(steamFmUser);
    }

    @Override
    public void delete(SteamFmUser steamFmUser) {
        steamFmUserDAO.delete(steamFmUser);
    }

}
