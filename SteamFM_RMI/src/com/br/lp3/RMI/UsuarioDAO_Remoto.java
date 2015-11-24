package com.br.lp3.RMI;

import com.br.lp3.entities.Usuario;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class UsuarioDAO_Remoto extends UnicastRemoteObject implements GenericoDAO_Remoto<Usuario> {

    private final UsuarioDAO DAO;

    public UsuarioDAO_Remoto() throws RemoteException {
        DAO = new UsuarioDAO();
    }

    @Override
    public void create(Usuario usuario) throws RemoteException {
        DAO.create(usuario);
    }

    @Override
    public List<Usuario> read() throws RemoteException {
        return DAO.read();
    }

    @Override
    public void update(Usuario usuario) throws RemoteException {
        DAO.update(usuario);
    }

    @Override
    public void delete(int id) throws RemoteException {
        DAO.delete(id);
    }

}
