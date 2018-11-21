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
public class ExamenTipoResultadoSexualPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EXAMEN")
    private long idExamen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EXAMEN_FISICO_SEXUAL")
    private long idExamenFisicoSexual;

    public ExamenTipoResultadoSexualPK() {
    }

    public ExamenTipoResultadoSexualPK(long idExamen, long idExamenFisicoSexual) {
        this.idExamen = idExamen;
        this.idExamenFisicoSexual = idExamenFisicoSexual;
    }

    public long getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(long idExamen) {
        this.idExamen = idExamen;
    }

    public long getIdExamenFisicoSexual() {
        return idExamenFisicoSexual;
    }

    public void setIdExamenFisicoSexual(long idExamenFisicoSexual) {
        this.idExamenFisicoSexual = idExamenFisicoSexual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idExamen;
        hash += (int) idExamenFisicoSexual;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenTipoResultadoSexualPK)) {
            return false;
        }
        ExamenTipoResultadoSexualPK other = (ExamenTipoResultadoSexualPK) object;
        if (this.idExamen != other.idExamen) {
            return false;
        }
        if (this.idExamenFisicoSexual != other.idExamenFisicoSexual) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ExamenTipoResultadoSexualPK[ idExamen=" + idExamen + ", idExamenFisicoSexual=" + idExamenFisicoSexual + " ]";
    }
    
}