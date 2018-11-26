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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GAME_GENRE_TO_MUSIC_RELEASE")
public class GameGenreToMusicRelease implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "game_genre_id")
    GameGenre gameGenre;

    @OneToOne
    @JoinColumn(name = "song_id")
    Song song;

    @OneToOne
    @JoinColumn(name = "album_id")
    Album album;

    @OneToOne
    @JoinColumn(name = "artist_id")
    Artist artist;

    public MusicRelease getMusicRelease() {
        if (song != null) {
            return song;
        } else if (album != null) {
            return album;
        } else if (artist != null) {
            return artist;
        } else {
            return null;
        }
    }

}
