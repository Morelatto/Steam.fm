package com.br.lp3.RMI;

import com.br.lp3.entities.Musica;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface MusicaManagerLocal extends Remote {

    public void create(Musica musica) throws RemoteException;

    public List<Musica> read() throws RemoteException;

    public void update(Musica musica) throws RemoteException;

    public void delete(int id) throws RemoteException;

}
