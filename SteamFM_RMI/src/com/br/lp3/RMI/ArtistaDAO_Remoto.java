package com.br.lp3.RMI;

import com.br.lp3.entities.Artista;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class ArtistaDAO_Remoto extends UnicastRemoteObject implements GenericoDAO_Remoto<Artista> {

    private final ArtistaDAO DAO;

    public ArtistaDAO_Remoto() throws RemoteException {
        DAO = new ArtistaDAO();
    }

    @Override
    public void create(Artista artista) throws RemoteException {
        DAO.create(artista);
    }

    @Override
    public List<Artista> read() throws RemoteException {
        return DAO.read();
    }

    @Override
    public void update(Artista artista) throws RemoteException {
        DAO.update(artista);
    }

    @Override
    public void delete(int id) throws RemoteException {
        DAO.delete(id);
    }

}
