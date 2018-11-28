package br.com.lp3.ejb;

import br.com.lp3.entities.SteamFmUser;
import br.com.lp3.utilities.RemoteDAOOperations;

import javax.ejb.Local;

@Local
public interface SteamFmUserManager {

    RemoteDAOOperations<SteamFmUser> getOperations();

}
