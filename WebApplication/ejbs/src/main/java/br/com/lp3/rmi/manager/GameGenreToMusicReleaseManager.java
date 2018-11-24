package br.com.lp3.rmi.manager;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.GameGenreToMusicRelease;
import br.com.lp3.rmi.dao.RemoteDAOOperations;

import java.rmi.Remote;

import javax.ejb.Local;

@Local
public interface GameGenreToMusicReleaseManager extends Remote {

    RemoteDAOOperations<GameGenreToMusicRelease> getOperations();

    GameGenreToMusicRelease getByGameGenre(GameGenre gameGenre);

}
