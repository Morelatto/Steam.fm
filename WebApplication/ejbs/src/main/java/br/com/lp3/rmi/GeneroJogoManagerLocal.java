package br.com.lp3.rmi;

import br.com.lp3.entities.GeneroJogo;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface GeneroJogoManagerLocal extends Remote {

    void create(GeneroJogo generoJogo);

    List<GeneroJogo> read();

    void update(GeneroJogo generoJogo);

    void delete(int id);

    List<GeneroJogo> getListaGenerosByUser(String username);

    List<GeneroJogo> getListaGenerosByGeneroName(List<String> generosNome);
}
