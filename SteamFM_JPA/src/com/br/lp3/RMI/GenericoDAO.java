package com.br.lp3.RMI;

import java.util.List;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 * @param <E>
 */
public interface GenericoDAO<E> {

    public void create(E e);

    public List<E> read();

    public void update(E e);

    public void delete(int id);

}
