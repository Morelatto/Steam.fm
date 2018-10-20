package br.com.lp3.rmi.dao.remote;

import br.com.lp3.entities.Album;
import br.com.lp3.rmi.dao.AlbumDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class AlbumDAO_Remoto extends UnicastRemoteObject implements GenericoDAO_Remoto<Album> {

    private final AlbumDAO DAO;

    public AlbumDAO_Remoto() throws RemoteException {
        DAO = new AlbumDAO();
    }

    @Override
    public void create(Album album) {
        DAO.create(album);
    }

    @Override
    public List<Album> read() {
        return DAO.read();
    }

    @Override
    public void update(Album album) {
        DAO.update(album);
    }

    @Override
    public void delete(int id) {
        DAO.delete(id);
    }

}
