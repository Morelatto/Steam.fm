package br.com.lp3.utilities;

import br.com.lp3.dao.RemoteDAO;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

// TODO refactor
// TODO maybe create singleton bean and inject it
@AllArgsConstructor
public class RemoteDAOOperations<T> {

    private RemoteDAO<T> remoteDAO;

    public Optional<T> get(long id) {
        try {
            return remoteDAO.get(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getAll() {
        try {
            return remoteDAO.getAll();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T save(T t) {
        try {
            return remoteDAO.save(t);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(T t) {
        try {
            remoteDAO.update(t);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void delete(T t) {
        try {
            remoteDAO.delete(t);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
