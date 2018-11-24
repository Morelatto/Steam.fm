package br.com.lp3.entities.dto;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.MusicRelease;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Game implements Serializable {

    Long id;

    String name;

    List<GameGenre> gameGenreList;

    List<MusicRelease> recommendationList;

}
