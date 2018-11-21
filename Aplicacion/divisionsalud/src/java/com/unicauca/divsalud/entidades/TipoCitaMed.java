/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sahydo
 */
@Entity
@Table(name = "tipo_cita_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCitaMed.findAll", query = "SELECT t FROM TipoCitaMed t"),
    @NamedQuery(name = "TipoCitaMed.findById", query = "SELECT t FROM TipoCitaMed t WHERE t.id = :id"),
    @NamedQuery(name = "TipoCitaMed.findByNombre", query = "SELECT t FROM TipoCitaMed t WHERE t.nombre = :nombre")})
public class TipoCitaMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipocitaID")
    private List<CitaMedicaMed> citaMedicaMedList;

    public TipoCitaMed() {
    }

    public TipoCitaMed(Integer id) {
        this.id = id;
    }

    public TipoCitaMed(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<CitaMedicaMed> getCitaMedicaMedList() {
        return citaMedicaMedList;
    }

    public void setCitaMedicaMedList(List<CitaMedicaMed> citaMedicaMedList) {
        this.citaMedicaMedList = citaMedicaMedList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCitaMed)) {
            return false;
        }
        TipoCitaMed other = (TipoCitaMed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
