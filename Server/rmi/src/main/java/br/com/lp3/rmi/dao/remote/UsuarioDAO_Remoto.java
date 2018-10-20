package br.com.lp3.rmi.dao.remote;

import br.com.lp3.entities.Usuario;
import br.com.lp3.rmi.dao.UsuarioDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
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
    public void create(Usuario usuario) {
        DAO.create(usuario);
    }

    @Override
    public List<Usuario> read() {
        return DAO.read();
    }

    @Override
    public void update(Usuario usuario) {
        DAO.update(usuario);
    }

    @Override
    public void delete(int id) {
        DAO.delete(id);
    }

}
