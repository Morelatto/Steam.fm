package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private Album albumId;

    @ManyToOne
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private Artist artistId;

    @ManyToOne
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private GameGenre gameGenreId;

    @ManyToOne
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private Music musicId;

}
