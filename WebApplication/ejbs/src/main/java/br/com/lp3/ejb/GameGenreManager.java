package br.com.lp3.ejb;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.rmi.RemoteDAOOperations;

import javax.ejb.Local;

@Local
public interface GameGenreManager {

    RemoteDAOOperations<GameGenre> getOperations();

    GameGenre getByName(String name);

}
