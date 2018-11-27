package br.com.lp3.ejb.beans;

import br.com.lp3.business.AlbumJSONParser;
import br.com.lp3.business.ArtistJSONParser;
import br.com.lp3.business.SongJSONParser;
import br.com.lp3.ejb.RecommendationManager;
import br.com.lp3.entities.Album;
import br.com.lp3.entities.Artist;
import br.com.lp3.entities.MusicRelease;
import br.com.lp3.entities.Song;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class RecommendationManagerBean implements RecommendationManager {

    public RecommendationManagerBean() {
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
