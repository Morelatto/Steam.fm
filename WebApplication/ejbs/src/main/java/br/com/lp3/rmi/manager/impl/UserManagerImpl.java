package br.com.lp3.rmi.manager.impl;

import br.com.lp3.entities.SystemUser;
import br.com.lp3.rmi.RemoteDAO;
import br.com.lp3.rmi.manager.UserManager;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFMConstants.RMI_SERVER_PORT;

@Stateless
public class UserManagerImpl extends UnicastRemoteObject implements UserManager {

    private RemoteDAO remoteDAO;

    public UserManagerImpl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        remoteDAO = (RemoteDAO) registry.lookup("UserDAO");
    }

    @Override
    public Optional<SystemUser> get(long id) {
        return remoteDAO.get(id);
    }

    @Override
    public List<SystemUser> getAll() {
        return remoteDAO.getAll();
    }

    @Override
    public void save(SystemUser systemUser) {
        remoteDAO.save(systemUser);
    }

    @Override
    public void update(SystemUser systemUser) {
        remoteDAO.update(systemUser);
    }

    @Override
    public void delete(SystemUser systemUser) {
        remoteDAO.delete(systemUser);
    }

}
