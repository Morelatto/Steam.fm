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
@Table(name = "GENERO_JOGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneroJogo.findAll", query = "SELECT g FROM GeneroJogo g"),
    @NamedQuery(name = "GeneroJogo.findByIdGeneroJogo", query = "SELECT g FROM GeneroJogo g WHERE g.idGeneroJogo = :idGeneroJogo"),
    @NamedQuery(name = "GeneroJogo.findByNomeGenero", query = "SELECT g FROM GeneroJogo g WHERE g.nomeGenero = :nomeGenero")})
public class GeneroJogo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GENERO_JOGO")
    private Integer idGeneroJogo;
    @Basic(optional = false)
    @Column(name = "NOME_GENERO")
    private String nomeGenero;
    @OneToMany(mappedBy = "idGeneroJogo")
    private Collection<Relacao> relacaoCollection;

    public GeneroJogo() {
    }

    public GeneroJogo(Integer idGeneroJogo) {
        this.idGeneroJogo = idGeneroJogo;
    }

    public GeneroJogo(Integer idGeneroJogo, String nomeGenero) {
        this.idGeneroJogo = idGeneroJogo;
        this.nomeGenero = nomeGenero;
    }

    public Integer getIdGeneroJogo() {
        return idGeneroJogo;
    }

    public void setIdGeneroJogo(Integer idGeneroJogo) {
        this.idGeneroJogo = idGeneroJogo;
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
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
        hash += (idGeneroJogo != null ? idGeneroJogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneroJogo)) {
            return false;
        }
        GeneroJogo other = (GeneroJogo) object;
        if ((this.idGeneroJogo == null && other.idGeneroJogo != null) || (this.idGeneroJogo != null && !this.idGeneroJogo.equals(other.idGeneroJogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.entities.GeneroJogo[ idGeneroJogo=" + idGeneroJogo + " ]";
    }
    
}
