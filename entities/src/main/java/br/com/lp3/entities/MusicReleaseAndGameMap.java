package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MusicReleaseAndGameMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id")
    private Album albumId;

    @OneToOne
    @JoinColumn(name = "id")
    private Artist artistId;

    @OneToOne
    @JoinColumn(name = "id")
    private GameGenre gameGenreId;

    @OneToOne
    @JoinColumn(name = "id")
    private Music musicId;

}
