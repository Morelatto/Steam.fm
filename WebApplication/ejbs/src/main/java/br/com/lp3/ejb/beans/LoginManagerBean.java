package br.com.lp3.ejb.beans;

import br.com.lp3.business.LoginJSONParser;
import br.com.lp3.ejb.LoginManager;
import br.com.lp3.ejb.SteamFmUserManager;
import br.com.lp3.entities.SteamFmUser;
import br.com.lp3.utilities.ServiceLocator;

import javax.ejb.Stateless;

@Stateless
public class LoginManagerBean implements LoginManager {

    public LoginManagerBean() {
    }

    @Override
    public SteamFmUser authorize(String login, String password) {
        SteamFmUserManager steamFmUserManager = ServiceLocator.getInstance().getSteamFmUserManager();
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
