package br.com.lp3.ejb;

import br.com.lp3.entities.SteamFmUser;

import javax.ejb.Local;

@Local
public interface LoginManager {

    SteamFmUser authorize(String login, String senha);

    String getSteamIdFromUsername(String userSteam);

}
