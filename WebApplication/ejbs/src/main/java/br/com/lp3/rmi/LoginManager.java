package br.com.lp3.rmi;

import br.com.lp3.business.LoginJSONParser;
import br.com.lp3.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class LoginManager extends UnicastRemoteObject implements LoginManagerLocal {

    @EJB
    private UsuarioManagerLocal usuarioManager;

    public LoginManager() throws RemoteException {
    }

    @Override
    public User authorize(String login, String senha) {
        for (User user : usuarioManager.read()) {
            if (login.equals(user.getLogin()) && senha.equals(user.getPassword())) {
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
