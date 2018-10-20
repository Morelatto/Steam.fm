package br.com.lp3.rmi;

import br.com.lp3.entities.Musica;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface MusicaManagerLocal extends Remote {

    void create(Musica musica);

    List<Musica> read();

    void update(Musica musica);

    void delete(int id);

}
