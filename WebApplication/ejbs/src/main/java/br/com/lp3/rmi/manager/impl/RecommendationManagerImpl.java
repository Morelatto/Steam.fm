package br.com.lp3.rmi.manager.impl;

import br.com.lp3.business.AlbumJSONParser;
import br.com.lp3.business.ArtistJSONParser;
import br.com.lp3.business.SongJSONParser;
import br.com.lp3.entities.Album;
import br.com.lp3.entities.Artist;
import br.com.lp3.entities.MusicRelease;
import br.com.lp3.entities.Song;
import br.com.lp3.rmi.manager.RecommendationManager;

import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;

@Stateless
public class RecommendationManagerImpl extends UnicastRemoteObject implements RecommendationManager {

    public RecommendationManagerImpl() throws RemoteException {
    }

    @Override
    public List<? extends MusicRelease> getRecommendation(MusicRelease musicRelease) {
        if (musicRelease instanceof Artist) {
            return ArtistJSONParser.getRecommendation((Artist) musicRelease);
        } else if (musicRelease instanceof Album) {
            return AlbumJSONParser.getRecommendation((Album) musicRelease);
        } else if (musicRelease instanceof Song) {
            return SongJSONParser.getRecommendation((Song) musicRelease);
        } else {
            return Collections.emptyList();
        }
    }

}
