package com.br.lp3.RMI;

import com.br.lp3.entities.Usuario;
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
public interface UsuarioManagerLocal extends Remote {

    public void create(Usuario usuario) throws RemoteException;

    public List<Usuario> read() throws RemoteException;

    public void update(Usuario usuario) throws RemoteException;

    public void delete(int id) throws RemoteException;

}
