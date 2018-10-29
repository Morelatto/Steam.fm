package br.com.lp3.rmi.manager;

import br.com.lp3.entities.Album;
import br.com.lp3.entities.Artist;
import br.com.lp3.entities.Music;

import javax.ejb.Local;
import java.rmi.Remote;
import java.util.List;

@Local
public interface RecommendationManager extends Remote {

    List<Music> getMusicaRecomendacao(List<Music> music);

    List<Album> getAlbumRecomendacao(List<Album> albuns);

    List<Artist> getArtistaRecomendacao(List<Artist> artist);

}
