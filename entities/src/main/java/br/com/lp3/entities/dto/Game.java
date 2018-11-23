package br.com.lp3.entities.dto;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.MusicRelease;

import java.util.List;

import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    Long id;

    String name;

    @OneToMany(mappedBy = "id")
    List<GameGenre> gameGenreList;

    List<MusicRelease> musicReleaseRecommendationList;

}
