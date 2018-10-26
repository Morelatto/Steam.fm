package br.com.lp3.dao.impl;

import br.com.lp3.dao.RemoteDAO;
import br.com.lp3.entities.Usuario;
import br.com.lp3.dao.impl.UsuarioDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

public class RemoteUsuarioDAO extends UnicastRemoteObject implements RemoteDAO<Usuario> {

    private final UsuarioDAO UsuarioDAO;

    public RemoteUsuarioDAO() throws RemoteException {
        UsuarioDAO = new UsuarioDAO();
    }

    @Override
    public Optional<Usuario> get(long id) {
        return UsuarioDAO.get(id);
    }

    @Override
    public List<Usuario> getAll() {
        return UsuarioDAO.getAll();
    }

    @Override
    public void save(Usuario usuario) {
        UsuarioDAO.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        UsuarioDAO.update(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        UsuarioDAO.delete(usuario);
    }

}
