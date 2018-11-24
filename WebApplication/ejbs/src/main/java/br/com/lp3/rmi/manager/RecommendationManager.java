package br.com.lp3.rmi.manager;

import br.com.lp3.entities.MusicRelease;

import java.rmi.Remote;
import java.util.List;

import javax.ejb.Local;

@Local
public interface RecommendationManager extends Remote {

    List<? extends MusicRelease> getRecommendation(MusicRelease musicRelease);

}
