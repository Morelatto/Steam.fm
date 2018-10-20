package br.com.lp3.rmi.dao.remote;

import br.com.lp3.entities.Artista;
import br.com.lp3.rmi.dao.ArtistaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
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
    public void create(Artista artista) {
        DAO.create(artista);
    }

    @Override
    public List<Artista> read() {
        return DAO.read();
    }

    @Override
    public void update(Artista artista) {
        DAO.update(artista);
    }

    @Override
    public void delete(int id) {
        DAO.delete(id);
    }

}
