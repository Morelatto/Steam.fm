package br.com.lp3.rmi;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

public interface RemoteDAO<T> extends Remote {

    Optional<T> get(Long id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);

}
