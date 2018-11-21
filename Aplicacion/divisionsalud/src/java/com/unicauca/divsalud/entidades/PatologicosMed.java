/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Danilo
 */
@Entity
@Table(name = "patologicos_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatologicosMed.findAll", query = "SELECT p FROM PatologicosMed p"),
    @NamedQuery(name = "PatologicosMed.findByIdx", query = "SELECT p FROM PatologicosMed p WHERE p.idx = :idx"),
    @NamedQuery(name = "PatologicosMed.findByNombre", query = "SELECT p FROM PatologicosMed p WHERE p.nombre = :nombre")})
public class PatologicosMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;

    public PatologicosMed() {
    }

    public PatologicosMed(Integer idx) {
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
        if (!(object instanceof PatologicosMed)) {
            return false;
        }
        PatologicosMed other = (PatologicosMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.PatologicosMed[ idx=" + idx + " ]";
    }
    
}
