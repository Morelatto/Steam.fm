package br.com.lp3.ejb.beans;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.ejb.SteamFmUserManager;
import br.com.lp3.entities.SteamFmUser;
import br.com.lp3.utilities.RemoteDAOOperations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.ejb.Stateless;

import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_HOST;
import static br.com.lp3.utilities.SteamFmConstants.RMI_SERVER_PORT;

@Stateless
public class SteamFmUserManagerBean implements SteamFmUserManager {

    private RemoteDAOOperations<SteamFmUser> operations;

    public SteamFmUserManagerBean() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOST, RMI_SERVER_PORT);
        RemoteDAO remoteDAO = (RemoteDAO) registry.lookup("SteamFmUserDAO");
        operations = new RemoteDAOOperations<SteamFmUser>(remoteDAO);
    }

    @Override
    public RemoteDAOOperations<SteamFmUser> getOperations() {
        return operations;
    }

}
