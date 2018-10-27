package br.com.lp3.rmi;

import br.com.lp3.entities.GameGenre;

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

    void create(GameGenre gameGenre);

    List<GameGenre> read();

    void update(GameGenre gameGenre);

    void delete(int id);

    List<GameGenre> getListaGenerosByUser(String username);

    List<GameGenre> getListaGenerosByGeneroName(List<String> generosNome);
}
