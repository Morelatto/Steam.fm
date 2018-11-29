package br.com.lp3.ejb.beans;

import br.com.lp3.business.LoginJSONParser;
import br.com.lp3.ejb.LoginManager;
import br.com.lp3.ejb.SteamFmUserManager;
import br.com.lp3.entities.SteamFmUser;
import br.com.lp3.utilities.ServiceLocator;

import javax.ejb.Stateless;

@Stateless
public class LoginManagerBean implements LoginManager {

    private SteamFmUserManager steamFmUserManager;

    public LoginManagerBean() {
        steamFmUserManager = ServiceLocator.getInstance().getSteamFmUserManager();
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
    public SteamFmUser register(String steamUsername) {
        SteamFmUser steamFmUser = steamFmUserManager.getBySteamUsername(steamUsername);
        if (steamFmUser == null) {
            steamFmUser = steamFmUserManager.getOperations().save(SteamFmUser
                    .builder()
                    .isAdmin(false)
                    .steamUser(steamUsername)
                    .steamId(LoginJSONParser.getSteamIdFromUsername(steamUsername))
                    .build());
        }
        return steamFmUser;
    }

}
