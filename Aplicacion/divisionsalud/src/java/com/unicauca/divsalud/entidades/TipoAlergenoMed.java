/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Danilo
 */
@Entity
@Table(name = "tipo_alergeno_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAlergenoMed.findAll", query = "SELECT t FROM TipoAlergenoMed t"),
    @NamedQuery(name = "TipoAlergenoMed.findByIdx", query = "SELECT t FROM TipoAlergenoMed t WHERE t.idx = :idx"),
    @NamedQuery(name = "TipoAlergenoMed.findByNombre", query = "SELECT t FROM TipoAlergenoMed t WHERE t.nombre = :nombre")})
public class TipoAlergenoMed implements Serializable {

    @OneToMany(mappedBy = "idxTipoAlergeno")
    private Collection<AlergenoMed> alergenoMedCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;

    public TipoAlergenoMed() {
    }

    public TipoAlergenoMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idx != null ? idx.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAlergenoMed)) {
            return false;
        }
        TipoAlergenoMed other = (TipoAlergenoMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.TipoAlergenoMed[ idx=" + idx + ", nombre = " + nombre + " ] ";
    }

    @XmlTransient
    public Collection<AlergenoMed> getAlergenoMedCollection() {
        return alergenoMedCollection;
    }

    public void setAlergenoMedCollection(Collection<AlergenoMed> alergenoMedCollection) {
        this.alergenoMedCollection = alergenoMedCollection;
    }
    
}
