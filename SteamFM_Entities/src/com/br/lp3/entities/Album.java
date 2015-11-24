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
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Entity
@Table(name = "ALBUM", schema = "STEAMFM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByIdAlbum", query = "SELECT a FROM Album a WHERE a.idAlbum = :idAlbum"),
    @NamedQuery(name = "Album.findByTituloAlbum", query = "SELECT a FROM Album a WHERE a.tituloAlbum = :tituloAlbum"),
    @NamedQuery(name = "Album.findByIdAlbumLastfm", query = "SELECT a FROM Album a WHERE a.idAlbumLastfm = :idAlbumLastfm"),
    @NamedQuery(name = "Album.findByImagem", query = "SELECT a FROM Album a WHERE a.imagem = :imagem"),
    @NamedQuery(name = "Album.findByDescricao", query = "SELECT a FROM Album a WHERE a.descricao = :descricao")})
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ALBUM")
    private Integer idAlbum;
    @Basic(optional = false)
    @Column(name = "TITULO_ALBUM")
    private String tituloAlbum;
    @Basic(optional = false)
    @Column(name = "ID_ALBUM_LASTFM")
    private String idAlbumLastfm;
    @Column(name = "IMAGEM")
    private String imagem;
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToMany(mappedBy = "idAlbum")
    private Collection<Relacao> relacaoCollection;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Album() {
    }

    public Album(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Album(Integer idAlbum, String tituloAlbum, String idAlbumLastfm) {
        this.idAlbum = idAlbum;
        this.tituloAlbum = tituloAlbum;
        this.idAlbumLastfm = idAlbumLastfm;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTituloAlbum() {
        return tituloAlbum;
    }

    public void setTituloAlbum(String tituloAlbum) {
        this.tituloAlbum = tituloAlbum;
    }

    public String getIdAlbumLastfm() {
        return idAlbumLastfm;
    }

    public void setIdAlbumLastfm(String idAlbumLastfm) {
        this.idAlbumLastfm = idAlbumLastfm;
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
        hash += (idAlbum != null ? idAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.idAlbum == null && other.idAlbum != null) || (this.idAlbum != null && !this.idAlbum.equals(other.idAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.entities.Album[ idAlbum=" + idAlbum + " ]";
    }
    
    public String getNome(){
        return tituloAlbum;
    }
    
}
