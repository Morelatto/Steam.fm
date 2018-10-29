package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Album;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface AlbumManager extends Remote {

    Optional<Album> get(long id);

    List<Album> getAll();

    void save(Album album);

    void update(Album album);

    void delete(Album album);

}
