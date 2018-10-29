package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Music;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface MusicManager extends Remote {

    Optional<Music> get(long id);

    List<Music> getAll();

    void save(Music music);

    void update(Music music);

    void delete(Music music);

}
