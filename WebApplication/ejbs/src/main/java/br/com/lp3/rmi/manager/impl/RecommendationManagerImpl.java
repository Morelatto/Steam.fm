package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.AlbumJSONParser;
import br.com.lp3.business.ArtistJSONParser;
import br.com.lp3.business.MusicJSONParser;
import br.com.lp3.entities.Album;
import br.com.lp3.entities.Artist;
import br.com.lp3.entities.Song;
import br.com.lp3.rmi.manager.RecommendationManager;

import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

@Stateless
public class RecommendationManagerImpl extends UnicastRemoteObject implements RecommendationManager {

    public RecommendationManagerImpl() throws RemoteException {
    }

    @Override
    public List<Song> getMusicaRecomendacao(List<Song> songs) {
        return MusicJSONParser.getMusicaRecomendacao(songs);
    }

    @Override
    public List<Album> getAlbumRecomendacao(List<Album> albuns) {
        return AlbumJSONParser.getAlbumRecomendacao(albuns);

    }

    @Override
    public List<Artist> getArtistaRecomendacao(List<Artist> artists) {
        return ArtistJSONParser.getArtistaRecomendacao(artists);
    }


}
