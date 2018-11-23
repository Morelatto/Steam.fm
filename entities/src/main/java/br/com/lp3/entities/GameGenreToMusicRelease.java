package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GAME_GENRE_TO_MUSIC_RELEASE")
public class GameGenreToMusicRelease implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "game_genre_id")
    GameGenre gameGenreId;

    @OneToOne
    @JoinColumn(name = "song_id")
    Song songId;

    @OneToOne
    @JoinColumn(name = "album_id")
    Album albumId;

    @OneToOne
    @JoinColumn(name = "artist_id")
    Artist artistId;

}
