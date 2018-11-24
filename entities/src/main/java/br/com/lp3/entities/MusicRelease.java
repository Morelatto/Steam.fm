package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class MusicRelease implements Serializable {

    @Id
    @Column(name = "last_fm_id")
    String lastFmId;

    String name;

    String image;

    String description;

    String url;

}
