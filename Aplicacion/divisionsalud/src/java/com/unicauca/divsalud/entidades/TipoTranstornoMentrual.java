/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "tipo_transtorno_mentrual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTranstornoMentrual.findAll", query = "SELECT t FROM TipoTranstornoMentrual t")
    , @NamedQuery(name = "TipoTranstornoMentrual.findByIdTransMens", query = "SELECT t FROM TipoTranstornoMentrual t WHERE t.idTransMens = :idTransMens")
    , @NamedQuery(name = "TipoTranstornoMentrual.findByNombreTranstorno", query = "SELECT t FROM TipoTranstornoMentrual t WHERE t.nombreTranstorno = :nombreTranstorno")})
public class TipoTranstornoMentrual implements Serializable {

    @Transient
    private boolean presenta;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TRANS_MENS")
    private Short idTransMens;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_TRANSTORNO")
    private String nombreTranstorno;
    @ManyToMany(mappedBy = "tipoTranstornoMentrualList")
    private List<AntecedentesPersonalesSexual> antecedentesPersonalesSexualList;

    public TipoTranstornoMentrual() {
    }

    public TipoTranstornoMentrual(Short idTransMens) {
        this.idTransMens = idTransMens;
    }

    public TipoTranstornoMentrual(Short idTransMens, String nombreTranstorno) {
        this.idTransMens = idTransMens;
        this.nombreTranstorno = nombreTranstorno;
    }

    public Short getIdTransMens() {
        return idTransMens;
    }

    public void setIdTransMens(Short idTransMens) {
        this.idTransMens = idTransMens;
    }

    public String getNombreTranstorno() {
        return nombreTranstorno;
    }

    public void setNombreTranstorno(String nombreTranstorno) {
        this.nombreTranstorno = nombreTranstorno;
    }

    public boolean isPresenta() {
        return presenta;
    }

    public void setPresenta(boolean presenta) {
        this.presenta = presenta;
    }

    @XmlTransient
    public List<AntecedentesPersonalesSexual> getAntecedentesPersonalesSexualList() {
        return antecedentesPersonalesSexualList;
    }

    public void setAntecedentesPersonalesSexualList(List<AntecedentesPersonalesSexual> antecedentesPersonalesSexualList) {
        this.antecedentesPersonalesSexualList = antecedentesPersonalesSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransMens != null ? idTransMens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTranstornoMentrual)) {
            return false;
        }
        TipoTranstornoMentrual other = (TipoTranstornoMentrual) object;
        if ((this.idTransMens == null && other.idTransMens != null) || (this.idTransMens != null && !this.idTransMens.equals(other.idTransMens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.TipoTranstornoMentrual[ idTransMens=" + idTransMens + " ]";
    }

}
