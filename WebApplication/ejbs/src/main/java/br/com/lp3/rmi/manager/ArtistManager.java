package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Artist;
import br.com.lp3.rmi.dao.RemoteDAOOperations;

import java.rmi.Remote;

import javax.ejb.Local;

@Local
public interface ArtistManager extends Remote {

    RemoteDAOOperations<Artist> getOperations();

}
