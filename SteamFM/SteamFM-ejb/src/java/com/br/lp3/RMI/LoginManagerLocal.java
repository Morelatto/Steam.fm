package com.br.lp3.RMI;

import com.br.lp3.entities.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface LoginManagerLocal extends Remote {

    public Usuario authorize(String login, String senha) throws RemoteException;

    public String getAnonSteamID(String userSteam) throws RemoteException;
}
