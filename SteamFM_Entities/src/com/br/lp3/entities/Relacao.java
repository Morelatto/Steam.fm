/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 31424635
 */
@Entity
@Table(name = "RELACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relacao.findAll", query = "SELECT r FROM Relacao r"),
    @NamedQuery(name = "Relacao.findByIdRelacao", query = "SELECT r FROM Relacao r WHERE r.idRelacao = :idRelacao")})
public class Relacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RELACAO")
    private Integer idRelacao;
    @JoinColumn(name = "ID_ALBUM", referencedColumnName = "ID_ALBUM")
    @ManyToOne
    private Album idAlbum;
    @JoinColumn(name = "ID_ARTISTA", referencedColumnName = "ID_ARTISTA")
    @ManyToOne
    private Artista idArtista;
    @JoinColumn(name = "ID_GENERO_JOGO", referencedColumnName = "ID_GENERO_JOGO")
    @ManyToOne
    private GeneroJogo idGeneroJogo;
    @JoinColumn(name = "ID_MUSICA", referencedColumnName = "ID_MUSICA")
    @ManyToOne
    private Musica idMusica;

    public Relacao() {
    }

    public Relacao(Integer idRelacao) {
        this.idRelacao = idRelacao;
    }

    public Integer getIdRelacao() {
        return idRelacao;
    }

    public void setIdRelacao(Integer idRelacao) {
        this.idRelacao = idRelacao;
    }

    public Album getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Album idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Artista getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Artista idArtista) {
        this.idArtista = idArtista;
    }

    public GeneroJogo getIdGeneroJogo() {
        return idGeneroJogo;
    }

    public void setIdGeneroJogo(GeneroJogo idGeneroJogo) {
        this.idGeneroJogo = idGeneroJogo;
    }

    public Musica getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(Musica idMusica) {
        this.idMusica = idMusica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacao != null ? idRelacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relacao)) {
            return false;
        }
        Relacao other = (Relacao) object;
        if ((this.idRelacao == null && other.idRelacao != null) || (this.idRelacao != null && !this.idRelacao.equals(other.idRelacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.entities.Relacao[ idRelacao=" + idRelacao + " ]";
    }
    
}
