/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author acer_acer
 */
@Embeddable
public class HistorialMetodosUsadosSexualPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_METODO_PLANIFICACION")
    private short idMetodoPlanificacion;

    public HistorialMetodosUsadosSexualPK() {
    }

    public HistorialMetodosUsadosSexualPK(int id, short idMetodoPlanificacion) {
        this.id = id;
        this.idMetodoPlanificacion = idMetodoPlanificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getIdMetodoPlanificacion() {
        return idMetodoPlanificacion;
    }

    public void setIdMetodoPlanificacion(short idMetodoPlanificacion) {
        this.idMetodoPlanificacion = idMetodoPlanificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idMetodoPlanificacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialMetodosUsadosSexualPK)) {
            return false;
        }
        HistorialMetodosUsadosSexualPK other = (HistorialMetodosUsadosSexualPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idMetodoPlanificacion != other.idMetodoPlanificacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.HistorialMetodosUsadosSexualPK[ id=" + id + ", idMetodoPlanificacion=" + idMetodoPlanificacion + " ]";
    }
    
}
