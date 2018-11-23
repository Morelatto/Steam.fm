package br.com.lp3.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
