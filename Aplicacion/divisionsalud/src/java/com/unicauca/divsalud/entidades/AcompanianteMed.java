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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "acompaniante_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcompanianteMed.findAll", query = "SELECT a FROM AcompanianteMed a"),
    @NamedQuery(name = "AcompanianteMed.findByIdx", query = "SELECT a FROM AcompanianteMed a WHERE a.idx = :idx"),
    @NamedQuery(name = "AcompanianteMed.findByNombre", query = "SELECT a FROM AcompanianteMed a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AcompanianteMed.findByTelefono", query = "SELECT a FROM AcompanianteMed a WHERE a.telefono = :telefono"),
    @NamedQuery(name = "AcompanianteMed.findByCelular", query = "SELECT a FROM AcompanianteMed a WHERE a.celular = :celular")})
public class AcompanianteMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 70)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 15)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 15)
    @Column(name = "CELULAR")
    private String celular;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Parentesco id;
    @OneToMany(mappedBy = "acompanianteMedIdx")
    private Collection<ConsultaMedicaMed> consultaMedicaMedCollection;

    public AcompanianteMed() {
    }

    public AcompanianteMed(Integer idx) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Parentesco getId() {
        return id;
    }

    public void setId(Parentesco id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<ConsultaMedicaMed> getConsultaMedicaMedCollection() {
        return consultaMedicaMedCollection;
    }

    public void setConsultaMedicaMedCollection(Collection<ConsultaMedicaMed> consultaMedicaMedCollection) {
        this.consultaMedicaMedCollection = consultaMedicaMedCollection;
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
        if (!(object instanceof AcompanianteMed)) {
            return false;
        }
        AcompanianteMed other = (AcompanianteMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.AcompanianteMed[ idx=" + idx + " ]";
    }
    
}
