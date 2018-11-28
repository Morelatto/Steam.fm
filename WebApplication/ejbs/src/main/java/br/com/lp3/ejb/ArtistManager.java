package br.com.lp3.ejb;

import br.com.lp3.entities.Artist;
import br.com.lp3.utilities.RemoteDAOOperations;

import javax.ejb.Local;

@Local
public interface ArtistManager {

    RemoteDAOOperations<Artist> getOperations();

}
