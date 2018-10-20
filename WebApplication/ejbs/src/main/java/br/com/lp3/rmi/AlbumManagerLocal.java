package br.com.lp3.rmi;

import br.com.lp3.entities.Album;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface AlbumManagerLocal extends Remote {

    void create(Album album);

    List<Album> read();

    void update(Album album);

    void delete(int id);

}
