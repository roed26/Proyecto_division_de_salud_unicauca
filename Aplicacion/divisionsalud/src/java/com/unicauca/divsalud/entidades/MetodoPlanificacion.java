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
 * @author acer_acer
 */
@Entity
@Table(name = "metodo_planificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MetodoPlanificacion.findAll", query = "SELECT m FROM MetodoPlanificacion m")
    , @NamedQuery(name = "MetodoPlanificacion.findByIdMetodoPlanificacion", query = "SELECT m FROM MetodoPlanificacion m WHERE m.idMetodoPlanificacion = :idMetodoPlanificacion")
    , @NamedQuery(name = "MetodoPlanificacion.findByNombreMetodo", query = "SELECT m FROM MetodoPlanificacion m WHERE m.nombreMetodo = :nombreMetodo")})
public class MetodoPlanificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_METODO_PLANIFICACION")
    private Short idMetodoPlanificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE_METODO")
    private String nombreMetodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metodoPlanificacion")
    private List<MetodoAdoptadoSexual> metodoAdoptadoSexualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metodoPlanificacion")
    private List<HistorialMetodosUsadosSexual> historialMetodosUsadosSexualList;

    public MetodoPlanificacion() {
    }

    public MetodoPlanificacion(Short idMetodoPlanificacion) {
        this.idMetodoPlanificacion = idMetodoPlanificacion;
    }

    public MetodoPlanificacion(Short idMetodoPlanificacion, String nombreMetodo) {
        this.idMetodoPlanificacion = idMetodoPlanificacion;
        this.nombreMetodo = nombreMetodo;
    }

    public Short getIdMetodoPlanificacion() {
        return idMetodoPlanificacion;
    }

    public void setIdMetodoPlanificacion(Short idMetodoPlanificacion) {
        this.idMetodoPlanificacion = idMetodoPlanificacion;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    @XmlTransient
    public List<MetodoAdoptadoSexual> getMetodoAdoptadoSexualList() {
        return metodoAdoptadoSexualList;
    }

    public void setMetodoAdoptadoSexualList(List<MetodoAdoptadoSexual> metodoAdoptadoSexualList) {
        this.metodoAdoptadoSexualList = metodoAdoptadoSexualList;
    }

    @XmlTransient
    public List<HistorialMetodosUsadosSexual> getHistorialMetodosUsadosSexualList() {
        return historialMetodosUsadosSexualList;
    }

    public void setHistorialMetodosUsadosSexualList(List<HistorialMetodosUsadosSexual> historialMetodosUsadosSexualList) {
        this.historialMetodosUsadosSexualList = historialMetodosUsadosSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetodoPlanificacion != null ? idMetodoPlanificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoPlanificacion)) {
            return false;
        }
        MetodoPlanificacion other = (MetodoPlanificacion) object;
        if ((this.idMetodoPlanificacion == null && other.idMetodoPlanificacion != null) || (this.idMetodoPlanificacion != null && !this.idMetodoPlanificacion.equals(other.idMetodoPlanificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.MetodoPlanificacion[ idMetodoPlanificacion=" + idMetodoPlanificacion + " ]";
    }
    
}
