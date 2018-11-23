package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Album;
import br.com.lp3.rmi.dao.RemoteDAOOperations;

import java.rmi.Remote;

import javax.ejb.Local;

@Local
public interface AlbumManager extends Remote {

    RemoteDAOOperations<Album> getOperations();

}
