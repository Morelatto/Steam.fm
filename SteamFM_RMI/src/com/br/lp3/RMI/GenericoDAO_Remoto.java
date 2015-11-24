package com.br.lp3.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 * @param <E>
 */
public interface GenericoDAO_Remoto<E> extends Remote {

    public void create(E e) throws RemoteException;

    public List<E> read() throws RemoteException;

    public void update(E e) throws RemoteException;

    public void delete(int id) throws RemoteException;

}
