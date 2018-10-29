package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Artist;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface ArtistManager extends Remote {

    Optional<Artist> get(long id);

    List<Artist> getAll();

    void save(Artist artist);

    void update(Artist artist);

    void delete(Artist artist);

}
