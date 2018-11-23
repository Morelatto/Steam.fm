package br.com.lp3.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface RemoteDAO<T> extends Remote {

    Optional<T> get(Long id) throws RemoteException;

    List<T> getAll() throws RemoteException;

    void save(T t) throws RemoteException;

    void update(T t) throws RemoteException;

    void delete(T t) throws RemoteException;

}
