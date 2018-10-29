package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.LoginJSONParser;
import br.com.lp3.entities.User;
import br.com.lp3.rmi.manager.LoginManager;
import br.com.lp3.rmi.manager.UserManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Stateless
public class LoginManagerImpl extends UnicastRemoteObject implements LoginManager {

    @EJB
    private UserManager userManager;

    public LoginManagerImpl() throws RemoteException {
    }

    @Override
    public User authorize(String login, String password) {
        // todo wtf
        for (User user : userManager.getAll()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String getAnonSteamID(String userSteam) {
        return LoginJSONParser.getSteamID(userSteam);
    }

}
