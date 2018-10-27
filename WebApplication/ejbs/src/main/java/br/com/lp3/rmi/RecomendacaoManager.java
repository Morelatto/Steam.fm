package br.com.lp3.rmi;

import br.com.lp3.business.AlbumJSONParser;
import br.com.lp3.business.ArtistaJSONParser;
import br.com.lp3.business.MusicaJSONParser;
import br.com.lp3.entities.Album;
import br.com.lp3.entities.Artist;
import br.com.lp3.entities.Music;

import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Stateless
public class RecomendacaoManager extends UnicastRemoteObject implements RecomendacaoManagerLocal {

    public RecomendacaoManager() throws RemoteException {
    }

    @Override
    public List<Music> getMusicaRecomendacao(List<Music> music) {
        return MusicaJSONParser.getMusicaRecomendacao(music);
    }

    @Override
    public List<Album> getAlbumRecomendacao(List<Album> albuns) {
        return AlbumJSONParser.getAlbumRecomendacao(albuns);

    }

    @Override
    public List<Artist> getArtistaRecomendacao(List<Artist> artists) {
        return ArtistaJSONParser.getArtistaRecomendacao(artists);
    }


}
