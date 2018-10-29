package br.com.lp3.rmi.manager;

import br.com.lp3.entities.User;

import javax.ejb.Local;
import java.rmi.Remote;

@Local
public interface LoginManager extends Remote {

    User authorize(String login, String senha);

    String getAnonSteamID(String userSteam);

}
