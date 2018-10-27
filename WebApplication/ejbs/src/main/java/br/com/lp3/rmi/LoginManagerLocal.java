package br.com.lp3.rmi;

import br.com.lp3.entities.User;

import javax.ejb.Local;
import java.rmi.Remote;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface LoginManagerLocal extends Remote {

    User authorize(String login, String senha);

    String getAnonSteamID(String userSteam);
}
