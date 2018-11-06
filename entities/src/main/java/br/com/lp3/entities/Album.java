package br.com.lp3.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String lastFmId;

    String image;

    String description;

    String url;

    @OneToMany(mappedBy = "albumId")
    Collection<MusicReleaseAndGameMap> musicReleaseAndGameMap;

    // @XmlTransient public Collection<MusicReleaseAndGameMap> getMusicReleaseAndGameMap() { return musicReleaseAndGameMap; }

}
