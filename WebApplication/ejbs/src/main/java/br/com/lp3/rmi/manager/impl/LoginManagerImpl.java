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

    // TODO remove this
    @EJB
    private SteamFmUserManager steamFmUserManager;

    public LoginManagerImpl() throws RemoteException {
    }

    @Override
    public SteamFmUser authorize(String login, String password) {
        // todo wtf
        for (SteamFmUser steamFmUser : steamFmUserManager.getOperations().getAll()) {
            if (login.equals(steamFmUser.getLogin()) && password.equals(steamFmUser.getPassword())) {
                return steamFmUser;
            }
        }
        return null;
    }

    @Override
    public String getAnonSteamID(String userSteam) {
        return LoginJSONParser.getSteamID(userSteam);
    }

}
