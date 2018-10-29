package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Game;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameManager {

    List<Game> getGamesByUser(String username);

}
