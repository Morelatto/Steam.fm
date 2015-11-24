package com.br.lp3.RMI;

import com.br.lp3.entities.Album;
import com.br.lp3.entities.Artista;
import com.br.lp3.entities.Musica;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface RecomendacaoManagerLocal extends Remote {

    public List<Musica> getMusicaRecomendacao(List<Musica> musica) throws RemoteException;

    public List<Album> getAlbumRecomendacao(List<Album> albuns) throws RemoteException;

    public List<Artista> getArtistaRecomendacao(List<Artista> artista) throws RemoteException;
}
