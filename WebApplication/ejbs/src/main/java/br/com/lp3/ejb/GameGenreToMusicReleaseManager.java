package br.com.lp3.ejb;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.GameGenreToMusicRelease;
import br.com.lp3.utilities.RemoteDAOOperations;

import javax.ejb.Local;

@Local
public interface GameGenreToMusicReleaseManager {

    RemoteDAOOperations<GameGenreToMusicRelease> getOperations();

    GameGenreToMusicRelease getByGameGenre(GameGenre gameGenre);

}
