package br.com.lp3.rmi.dao.remote;

import java.rmi.Remote;
import java.util.List;

/**
 * @param <E>
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public interface GenericoDAO_Remoto<E> extends Remote {

    void create(E e);

    List<E> read();

    void update(E e);

    void delete(int id);

}
