package br.com.lp3.rmi.dao;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoteDAOOperations<T> {

    private RemoteDAO<T> remoteDAO;

    public Optional<T> get(long id) {
        return remoteDAO.get(id);
    }

    public List<T> getAll() {
        return remoteDAO.getAll();
    }

    public void save(T t) {
        remoteDAO.save(t);
    }

    public void update(T t) {
        remoteDAO.update(t);
    }

    public void delete(T t) {
        remoteDAO.delete(t);
    }

}
