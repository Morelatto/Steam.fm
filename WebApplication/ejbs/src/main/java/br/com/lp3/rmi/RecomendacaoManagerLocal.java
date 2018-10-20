package br.com.lp3.rmi;

import br.com.lp3.entities.Album;
import br.com.lp3.entities.Artista;
import br.com.lp3.entities.Musica;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Local
public interface RecomendacaoManagerLocal extends Remote {

    List<Musica> getMusicaRecomendacao(List<Musica> musica);

    List<Album> getAlbumRecomendacao(List<Album> albuns);

    List<Artista> getArtistaRecomendacao(List<Artista> artista);
}
