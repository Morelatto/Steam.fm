package br.com.lp3.ejb;

import br.com.lp3.entities.Song;
import br.com.lp3.rmi.RemoteDAOOperations;

import javax.ejb.Local;

@Local
public interface SongManager {

    RemoteDAOOperations<Song> getOperations();

}
