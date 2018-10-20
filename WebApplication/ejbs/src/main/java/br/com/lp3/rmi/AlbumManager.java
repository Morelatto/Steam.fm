package br.com.lp3.rmi;

import br.com.lp3.entities.Album;

import javax.ejb.Stateless;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class AlbumManager extends UnicastRemoteObject implements AlbumManagerLocal {

    Registry registro;
    GenericoDAO_Remoto servico;

    public AlbumManager() throws RemoteException, NotBoundException {
        registro = LocateRegistry.getRegistry("localhost", 1099);
        servico = (GenericoDAO_Remoto) registro.lookup("AlbumDAO");
    }

    @Override
    public void create(Album album) {
        servico.create(album);
    }

    @Override
    public List<Album> read() {
        return (List<Album>) servico.read();
    }

    @Override
    public void update(Album album) {
        servico.update(album);
    }

    @Override
    public void delete(int id) {
        servico.delete(id);
    }
}
