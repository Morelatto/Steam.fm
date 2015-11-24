package com.br.lp3.RMI;

import com.br.lp3.entities.GeneroJogo;
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
public interface GeneroJogoManagerLocal extends Remote {

    public void create(GeneroJogo generoJogo) throws RemoteException;

    public List<GeneroJogo> read() throws RemoteException;

    public void update(GeneroJogo generoJogo) throws RemoteException;

    public void delete(int id) throws RemoteException;

    public List<GeneroJogo> getListaGenerosByUser(String username) throws RemoteException;

    public List<GeneroJogo> getListaGenerosByGeneroName(List<String> generosNome) throws RemoteException;
}
