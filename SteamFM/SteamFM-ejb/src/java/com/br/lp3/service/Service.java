/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.service;

import com.br.lp3.RMI.GeneroJogoManagerLocal;
import com.br.lp3.RMI.JogoManagerLocal;
import com.br.lp3.RMI.LoginManagerLocal;
import com.br.lp3.RMI.RecomendacaoManagerLocal;
import com.br.lp3.RMI.RelacaoManagerLocal;
import com.br.lp3.entities.Album;
import com.br.lp3.entities.Artista;
import com.br.lp3.entities.GeneroJogo;
import com.br.lp3.entities.Jogo;
import com.br.lp3.entities.Musica;
import com.br.lp3.entities.Relacao;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Lucas
 */
@Path("service")
@Stateless
public class Service {

    @EJB
    private LoginManagerLocal loginManager;
    @EJB
    private RecomendacaoManagerLocal recomendacaoManager;
    @EJB
    private RelacaoManagerLocal relacaoManager;
    @EJB
    private JogoManagerLocal jogoManager;
    @EJB
    private GeneroJogoManagerLocal generoJogoManager;

    @GET
    @Produces("application/xml")
    public Jogo processGet() {
        return new Jogo();
    }

    @Path("/jogos/{userSteam}")
    @GET
    @Produces("application/xml")
    public List<Jogo> getJogosByUser(@PathParam("userSteam") String userSteam) {
        List<Jogo> listaJogos = new ArrayList<>();
        try {
            String steamID = loginManager.getAnonSteamID(userSteam);
            listaJogos = jogoManager.getJogosByUser(steamID);
        } catch (RemoteException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaJogos;
    }

}
