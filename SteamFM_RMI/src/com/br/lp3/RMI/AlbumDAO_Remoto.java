package com.br.lp3.RMI;

import com.br.lp3.entities.Album;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
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
    public void create(Album album) throws RemoteException {
        DAO.create(album);
    }

    @Override
    public List<Album> read() throws RemoteException {
        return DAO.read();
    }

    @Override
    public void update(Album album) throws RemoteException {
        DAO.update(album);
    }

    @Override
    public void delete(int id) throws RemoteException {
        DAO.delete(id);
    }

}
