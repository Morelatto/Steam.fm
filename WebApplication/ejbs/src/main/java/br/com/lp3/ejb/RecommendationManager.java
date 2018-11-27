package br.com.lp3.ejb;

import br.com.lp3.entities.MusicRelease;

import java.util.List;

import javax.ejb.Local;

@Local
public interface RecommendationManager {

    List<? extends MusicRelease> getRecommendation(MusicRelease musicRelease);

}
