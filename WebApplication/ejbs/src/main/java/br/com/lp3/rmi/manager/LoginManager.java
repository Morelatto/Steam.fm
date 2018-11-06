package br.com.lp3.rmi.manager;

import br.com.lp3.entities.SystemUser;

import javax.ejb.Local;
import java.rmi.Remote;

@Local
public interface LoginManager extends Remote {

    SystemUser authorize(String login, String senha);

    String getAnonSteamID(String userSteam);

}
