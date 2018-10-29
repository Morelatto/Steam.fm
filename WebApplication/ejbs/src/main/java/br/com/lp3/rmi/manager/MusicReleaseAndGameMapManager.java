package br.com.lp3.rmi.manager;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.MusicReleaseAndGameMap;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface MusicReleaseAndGameMapManager extends Remote {

    Optional<MusicReleaseAndGameMap> get(long id);

    List<MusicReleaseAndGameMap> getAll();

    void save(MusicReleaseAndGameMap musicReleaseAndGameMap);

    void update(MusicReleaseAndGameMap musicReleaseAndGameMap);

    void delete(MusicReleaseAndGameMap musicReleaseAndGameMap);

    List<MusicReleaseAndGameMap> getListaRelacao(List<GameGenre> listaGameGenres);

}
