package br.com.lp3.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
@Entity
@Table(name = "ARTISTA", schema = "STEAMFM")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Artista.findAll", query = "SELECT a FROM Artista a"),
        @NamedQuery(name = "Artista.findByIdArtista", query = "SELECT a FROM Artista a WHERE a.idArtista = :idArtista"),
        @NamedQuery(name = "Artista.findByNomeArtista", query = "SELECT a FROM Artista a WHERE a.nomeArtista = :nomeArtista"),
        @NamedQuery(name = "Artista.findByIdArtistaLastfm", query = "SELECT a FROM Artista a WHERE a.idArtistaLastfm = :idArtistaLastfm"),
        @NamedQuery(name = "Artista.findByImagem", query = "SELECT a FROM Artista a WHERE a.imagem = :imagem"),
        @NamedQuery(name = "Artista.findByDescricao", query = "SELECT a FROM Artista a WHERE a.descricao = :descricao")})
public class Artista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ARTISTA")
    private Integer idArtista;
    @Basic(optional = false)
    @Column(name = "NOME_ARTISTA")
    private String nomeArtista;
    @Basic(optional = false)
    @Column(name = "ID_ARTISTA_LASTFM")
    private String idArtistaLastfm;
    @Column(name = "IMAGEM")
    private String imagem;
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToMany(mappedBy = "idArtista")
    private Collection<Relacao> relacaoCollection;
    private String url;

    public Artista() {
    }

    public Artista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Artista(Integer idArtista, String nomeArtista, String idArtistaLastfm) {
        this.idArtista = idArtista;
        this.nomeArtista = nomeArtista;
        this.idArtistaLastfm = idArtistaLastfm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public String getIdArtistaLastfm() {
        return idArtistaLastfm;
    }

    public void setIdArtistaLastfm(String idArtistaLastfm) {
        this.idArtistaLastfm = idArtistaLastfm;
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
        hash += (idArtista != null ? idArtista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artista)) {
            return false;
        }
        Artista other = (Artista) object;
        return (this.idArtista != null || other.idArtista == null) && (this.idArtista == null || this.idArtista.equals(other.idArtista));
    }

    @Override
    public String toString() {
        return "br.com.lp3.entities.Artista[ idArtista=" + idArtista + " ]";
    }

    public String getNome() {
        return nomeArtista;
    }
}
