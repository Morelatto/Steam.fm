package br.com.lp3.rmi.manager;

import br.com.lp3.entities.GameGenre;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface GameGenreManager extends Remote {

    Optional<GameGenre> get(long id);

    List<GameGenre> getAll();

    void save(GameGenre gameGenre);

    void update(GameGenre gameGenre);

    void delete(GameGenre gameGenre);

    List<GameGenre> getGameGenresByUser(String username);

    List<GameGenre> getGameGenreListByGameGenreNames(List<String> gameGenreNames);

}
