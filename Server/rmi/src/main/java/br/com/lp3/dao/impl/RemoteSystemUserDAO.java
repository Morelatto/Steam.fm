package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.SystemUser;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteSystemUserDAO extends UnicastRemoteObject implements RemoteDAO<SystemUser> {

    private final SystemUserDAO systemUserDAO;

    public RemoteSystemUserDAO() throws RemoteException {
        systemUserDAO = new SystemUserDAO();
    }

    @Override
    public Optional<SystemUser> get(long id) {
        return systemUserDAO.get(id);
    }

    @Override
    public List<SystemUser> getAll() {
        return systemUserDAO.getAll();
    }

    @Override
    public void save(SystemUser systemUser) {
        systemUserDAO.save(systemUser);
    }

    @Override
    public void update(SystemUser systemUser) {
        systemUserDAO.update(systemUser);
    }

    @Override
    public void delete(SystemUser systemUser) {
        systemUserDAO.delete(systemUser);
    }

}
