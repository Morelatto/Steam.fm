package com.br.lp3.RMI;

import com.br.lp3.entities.GeneroJogo;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
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
    public void create(GeneroJogo generoJogo) throws RemoteException {
        DAO.create(generoJogo);
    }

    @Override
    public List<GeneroJogo> read() throws RemoteException {
        return DAO.read();
    }

    @Override
    public void update(GeneroJogo generoJogo) throws RemoteException {
        DAO.update(generoJogo);
    }

    @Override
    public void delete(int id) throws RemoteException {
        DAO.delete(id);
    }

}
