package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="GAME_GENRE")
public class GameGenre implements Serializable {

    @Id
    @Column(name = "game_genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

}
