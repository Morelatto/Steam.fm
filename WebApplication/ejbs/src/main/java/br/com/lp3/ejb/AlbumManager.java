package br.com.lp3.ejb;

import br.com.lp3.entities.Album;
import br.com.lp3.utilities.RemoteDAOOperations;

import javax.ejb.Local;

@Local
public interface AlbumManager {

    RemoteDAOOperations<Album> getOperations();

}
