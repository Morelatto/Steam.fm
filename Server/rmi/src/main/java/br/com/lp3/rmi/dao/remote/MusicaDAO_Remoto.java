package br.com.lp3.rmi.dao.remote;

import br.com.lp3.entities.Musica;
import br.com.lp3.rmi.dao.MusicaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class MusicaDAO_Remoto extends UnicastRemoteObject implements GenericoDAO_Remoto<Musica> {

    private final MusicaDAO DAO;

    public MusicaDAO_Remoto() throws RemoteException {
        DAO = new MusicaDAO();
    }

    @Override
    public void create(Musica musica) {
        DAO.create(musica);
    }

    @Override
    public List<Musica> read() {
        return DAO.read();
    }

    @Override
    public void update(Musica musica) {
        DAO.update(musica);
    }

    @Override
    public void delete(int id) {
        DAO.delete(id);
    }

}
