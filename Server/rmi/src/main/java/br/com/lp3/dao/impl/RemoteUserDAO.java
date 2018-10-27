package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteUserDAO extends UnicastRemoteObject implements RemoteDAO<User> {

    private final UserDAO userDAO;

    public RemoteUserDAO() throws RemoteException {
        userDAO = new UserDAO();
    }

    @Override
    public Optional<User> get(long id) {
        return userDAO.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

}
