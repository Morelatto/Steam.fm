package br.com.lp3.rmi;

import br.com.lp3.entities.Music;

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
public class MusicaManager extends UnicastRemoteObject implements MusicaManagerLocal {

    Registry registro;
    GenericoDAO_Remoto servico;

    public MusicaManager() throws RemoteException, NotBoundException {
        registro = LocateRegistry.getRegistry("localhost", 1099);
        servico = (GenericoDAO_Remoto) registro.lookup("MusicaDAO");
    }

    @Override
    public void create(Music music) {
        servico.create(music);
    }

    @Override
    public List<Music> read() {
        return (List<Music>) servico.read();
    }

    @Override
    public void update(Music music) {
        servico.update(music);
    }

    @Override
    public void delete(int id) {
        servico.delete(id);
    }
}
