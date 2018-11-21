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
public class ExamenOtroTipoResultadoSexualPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EXAMEN_FISICO_SEXUAL")
    private long idExamenFisicoSexual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OTRO_EXAMEN")
    private long idOtroExamen;

    public ExamenOtroTipoResultadoSexualPK() {
    }

    public ExamenOtroTipoResultadoSexualPK(long idExamenFisicoSexual, long idOtroExamen) {
        this.idExamenFisicoSexual = idExamenFisicoSexual;
        this.idOtroExamen = idOtroExamen;
    }

    public long getIdExamenFisicoSexual() {
        return idExamenFisicoSexual;
    }

    public void setIdExamenFisicoSexual(long idExamenFisicoSexual) {
        this.idExamenFisicoSexual = idExamenFisicoSexual;
    }

    public long getIdOtroExamen() {
        return idOtroExamen;
    }

    public void setIdOtroExamen(long idOtroExamen) {
        this.idOtroExamen = idOtroExamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idExamenFisicoSexual;
        hash += (int) idOtroExamen;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenOtroTipoResultadoSexualPK)) {
            return false;
        }
        ExamenOtroTipoResultadoSexualPK other = (ExamenOtroTipoResultadoSexualPK) object;
        if (this.idExamenFisicoSexual != other.idExamenFisicoSexual) {
            return false;
        }
        if (this.idOtroExamen != other.idOtroExamen) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ExamenOtroTipoResultadoSexualPK[ idExamenFisicoSexual=" + idExamenFisicoSexual + ", idOtroExamen=" + idOtroExamen + " ]";
    }
    
}
