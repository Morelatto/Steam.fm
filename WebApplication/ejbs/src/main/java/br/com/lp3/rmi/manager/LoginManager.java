package br.com.lp3.rmi.manager;

import br.com.lp3.entities.SteamFmUser;

import javax.ejb.Local;
import java.rmi.Remote;

@Local
public interface LoginManager extends Remote {

    SteamFmUser authorize(String login, String senha);

    String getSteamIdFromUsername(String userSteam);

}
