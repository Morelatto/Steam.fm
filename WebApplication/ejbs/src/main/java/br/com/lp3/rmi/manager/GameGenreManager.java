package br.com.lp3.rmi.manager;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.rmi.dao.RemoteDAOOperations;

import java.rmi.Remote;

import javax.ejb.Local;

@Local
public interface GameGenreManager extends Remote {

    RemoteDAOOperations<GameGenre> getOperations();

    GameGenre getByName(String name);

}
