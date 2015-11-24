/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 31424635
 */
@Entity
@Table(name = "MUSICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Musica.findAll", query = "SELECT m FROM Musica m"),
    @NamedQuery(name = "Musica.findByIdMusica", query = "SELECT m FROM Musica m WHERE m.idMusica = :idMusica"),
    @NamedQuery(name = "Musica.findByTituloMusica", query = "SELECT m FROM Musica m WHERE m.tituloMusica = :tituloMusica"),
    @NamedQuery(name = "Musica.findByIdMusicaLastfm", query = "SELECT m FROM Musica m WHERE m.idMusicaLastfm = :idMusicaLastfm"),
    @NamedQuery(name = "Musica.findByImagem", query = "SELECT m FROM Musica m WHERE m.imagem = :imagem"),
    @NamedQuery(name = "Musica.findByDescricao", query = "SELECT m FROM Musica m WHERE m.descricao = :descricao")})
public class Musica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MUSICA")
    private Integer idMusica;
    @Basic(optional = false)
    @Column(name = "TITULO_MUSICA")
    private String tituloMusica;
    @Basic(optional = false)
    @Column(name = "ID_MUSICA_LASTFM")
    private String idMusicaLastfm;
    @Column(name = "IMAGEM")
    private String imagem;
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToMany(mappedBy = "idMusica")
    private Collection<Relacao> relacaoCollection;

    public Musica() {
    }

    public Musica(Integer idMusica) {
        this.idMusica = idMusica;
    }

    public Musica(Integer idMusica, String tituloMusica, String idMusicaLastfm) {
        this.idMusica = idMusica;
        this.tituloMusica = tituloMusica;
        this.idMusicaLastfm = idMusicaLastfm;
    }

    public Integer getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(Integer idMusica) {
        this.idMusica = idMusica;
    }

    public String getTituloMusica() {
        return tituloMusica;
    }

    public void setTituloMusica(String tituloMusica) {
        this.tituloMusica = tituloMusica;
    }

    public String getIdMusicaLastfm() {
        return idMusicaLastfm;
    }

    public void setIdMusicaLastfm(String idMusicaLastfm) {
        this.idMusicaLastfm = idMusicaLastfm;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Relacao> getRelacaoCollection() {
        return relacaoCollection;
    }

    public void setRelacaoCollection(Collection<Relacao> relacaoCollection) {
        this.relacaoCollection = relacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMusica != null ? idMusica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Musica)) {
            return false;
        }
        Musica other = (Musica) object;
        if ((this.idMusica == null && other.idMusica != null) || (this.idMusica != null && !this.idMusica.equals(other.idMusica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.entities.Musica[ idMusica=" + idMusica + " ]";
    }
    
}
