package br.com.lp3.rmi.manager;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.dto.Game;
import br.com.lp3.rmi.dao.RemoteDAOOperations;

import java.rmi.Remote;
import java.util.List;

import javax.ejb.Local;

@Local
public interface GameGenreManager extends Remote {

    RemoteDAOOperations<GameGenre> getOperations();

    List<GameGenre> getGameGenres(List<Game> gameList);

    List<GameGenre> getGameGenreListByGameGenreNames(List<String> gameGenreNames);

}
