package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Artist extends MusicRelease implements Serializable {

    @Builder
    public Artist(String lastFmId, String name, String image, String description, String url) {
        super(lastFmId, name, image, description, url);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "lastFmId='" + lastFmId + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
