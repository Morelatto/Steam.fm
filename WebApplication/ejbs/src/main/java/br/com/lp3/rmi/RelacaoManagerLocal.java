package br.com.lp3.rmi;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.MusicReleaseAndGameMap;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface RelacaoManagerLocal extends Remote {

    void create(MusicReleaseAndGameMap musicReleaseAndGameMap);

    List<MusicReleaseAndGameMap> read();

    void update(MusicReleaseAndGameMap musicReleaseAndGameMap);

    void delete(int id);

    List<MusicReleaseAndGameMap> getListaRelacao(List<GameGenre> listaGameGenres);

}
