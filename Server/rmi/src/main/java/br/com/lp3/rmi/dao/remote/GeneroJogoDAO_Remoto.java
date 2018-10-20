package br.com.lp3.rmi.dao.remote;

import br.com.lp3.entities.GeneroJogo;
import br.com.lp3.rmi.dao.GeneroJogoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class GeneroJogoDAO_Remoto extends UnicastRemoteObject implements GenericoDAO_Remoto<GeneroJogo> {

    private final GeneroJogoDAO DAO;

    public GeneroJogoDAO_Remoto() throws RemoteException {
        DAO = new GeneroJogoDAO();
    }

    @Override
    public void create(GeneroJogo generoJogo) {
        DAO.create(generoJogo);
    }

    @Override
    public List<GeneroJogo> read() {
        return DAO.read();
    }

    @Override
    public void update(GeneroJogo generoJogo) {
        DAO.update(generoJogo);
    }

    @Override
    public void delete(int id) {
        DAO.delete(id);
    }

}
