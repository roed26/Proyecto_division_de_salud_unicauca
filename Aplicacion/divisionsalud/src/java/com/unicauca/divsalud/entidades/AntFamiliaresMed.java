/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ant_familiares_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntFamiliaresMed.findAll", query = "SELECT a FROM AntFamiliaresMed a"),
    @NamedQuery(name = "AntFamiliaresMed.findByIdx", query = "SELECT a FROM AntFamiliaresMed a WHERE a.idx = :idx"),
    @NamedQuery(name = "AntFamiliaresMed.findByNombre", query = "SELECT a FROM AntFamiliaresMed a WHERE a.nombre = :nombre")})
public class AntFamiliaresMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antFamiliaresMedIdx")
    private Collection<AntFamiliarConsultaMed> antFamiliarConsultaMedCollection;

    public AntFamiliaresMed() {
    }

    public AntFamiliaresMed(Integer idx) {
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

    @XmlTransient
    public Collection<AntFamiliarConsultaMed> getAntFamiliarConsultaMedCollection() {
        return antFamiliarConsultaMedCollection;
    }

    public void setAntFamiliarConsultaMedCollection(Collection<AntFamiliarConsultaMed> antFamiliarConsultaMedCollection) {
        this.antFamiliarConsultaMedCollection = antFamiliarConsultaMedCollection;
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
        if (!(object instanceof AntFamiliaresMed)) {
            return false;
        }
        AntFamiliaresMed other = (AntFamiliaresMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.AntFamiliaresMed[ idx=" + idx + " ]";
    }
    
}
