package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Song;
import br.com.lp3.rmi.dao.RemoteDAOOperations;

import java.rmi.Remote;

import javax.ejb.Local;

@Local
public interface SongManager extends Remote {

    RemoteDAOOperations<Song> getOperations();

}
