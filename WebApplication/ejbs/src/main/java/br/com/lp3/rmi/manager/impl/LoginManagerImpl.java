package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.LoginJSONParser;
import br.com.lp3.entities.SteamFmUser;
import br.com.lp3.rmi.manager.LoginManager;
import br.com.lp3.rmi.manager.SteamFmUserManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Stateless
public class LoginManagerImpl extends UnicastRemoteObject implements LoginManager {

    @EJB
    private SteamFmUserManager steamFmUserManager;

    public LoginManagerImpl() throws RemoteException {
    }

    @Override
    public SteamFmUser authorize(String login, String password) {
        return steamFmUserManager
                .getOperations()
                .getAll()
                .stream()
                .filter(steamFmUser -> login.equals(steamFmUser.getLogin())
                        && password.equals(steamFmUser.getPassword()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getSteamIdFromUsername(String userSteam) {
        return LoginJSONParser.getSteamIdFromUsername(userSteam);
    }

}
