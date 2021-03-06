package br.com.lp3.entities;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Song extends MusicRelease {

    @Builder
    public Song(String lastFmId, String name, String image, String description, String url) {
        super(lastFmId, name, image, description, url);
    }

    @Override
    public String toString() {
        return "Song{" +
                "lastFmId='" + lastFmId + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
