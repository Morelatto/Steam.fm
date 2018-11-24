package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "STEAM_FM_USER")
public class SteamFmUser implements Serializable {

    @Id
    @Column(name = "steam_fm_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;

    String password;

    @Column(name = "steam_user")
    String steamUser;

}
