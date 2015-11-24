package com.br.lp3.RMI;

import com.br.lp3.business.LoginJSONParser;
import com.br.lp3.entities.Usuario;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
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
    public Usuario authorize(String login, String senha) throws RemoteException {
        for (Usuario usuario : usuarioManager.read()) {
            if (login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public String getAnonSteamID(String userSteam) throws RemoteException {
        return LoginJSONParser.getSteamID(userSteam);
    }

}
